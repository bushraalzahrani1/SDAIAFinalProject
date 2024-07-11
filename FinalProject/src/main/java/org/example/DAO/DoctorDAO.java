package org.example.DAO;

import org.example.DTO.DoctorFilterDTO;
import org.example.Model.Doctor;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO  implements Serializable {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";
    private  static final String SELECT_ALL_DOCTORS = "select * from DOCTORS";
    private static final String SELECT_ONE_DOCTOR = "select * from DOCTORS where doctor_id = ?";
    private static final String SELECT_ONE_DOCTOR_BY_DOCTOR_NAME = "select * from DOCTORS where doctor_name = ?";
    private static final String SELECT_ONE_DOCTOR_BY_DOCTOR_SPECIALTY = "select * from DOCTORS where doctor_specialty = ?";
    private static final String INSERT_DOCTOR = "INSERT INTO DOCTORS (doctor_name, doctor_specialty, doctor_email, doctor_password, doctor_phone) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_DOCTOR = "update DOCTORS set doctor_name = ?, doctor_specialty = ?, doctor_email = ? , doctor_password = ? , doctor_phone = ? where doctor_id =?";
    private static final String SELECT_DOCTOR_WITH_AVAILABLELITY = "select * from DOCTORS join SCHEDULES on DOCTORS.doctor_id = SCHEDULES.doctor_id where doctor_name = ?";
    private static final String DELETE_DOCTOR = "delete from DOCTORS where doctor_id = ?";


    public void INSERT_DOCTOR (Doctor doctor) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_DOCTOR);
//        st.setInt(1, doctor.getDoctor_id());
        st.setString(1, doctor.getDoctor_name());
        st.setString(2, doctor.getDoctor_specialty());
        st.setString(3, doctor.getDoctor_email());
        st.setString(4, doctor.getDoctor_password());
        st.setString(5, doctor.getDoctor_phone());
        st.executeUpdate();
    }

    public Doctor SELECT_ONE_DOCTOR(Integer doctor_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_DOCTOR);
        st.setInt(1, doctor_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Doctor(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Doctor> SELECT_ALL_DOCTORS(DoctorFilterDTO filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        if( filter.getDoctor_name()!= null) {
            st = conn.prepareStatement(SELECT_ONE_DOCTOR_BY_DOCTOR_NAME);
            st.setString(1, filter.getDoctor_name());

        }
        else if(filter.getDoctor_specialty() != null) {
            st = conn.prepareStatement(SELECT_ONE_DOCTOR_BY_DOCTOR_SPECIALTY);
            st.setString(1, filter.getDoctor_specialty());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_DOCTORS);
        }


        ResultSet rs = st.executeQuery();
        ArrayList<Doctor> doctor = new ArrayList<>();
        while (rs.next()) {
            doctor.add(new Doctor(rs));
        }

        return doctor;
    }



    public void UPDATE_DOCTOR(Doctor doctor) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_DOCTOR);
        st.setString(1, doctor.getDoctor_name());
        st.setString(2, doctor.getDoctor_specialty());
        st.setString(3, doctor.getDoctor_email());
        st.setString(4, doctor.getDoctor_password());
        st.setString(5, doctor.getDoctor_phone());
        st.setInt(6, doctor.getDoctor_id());
        st.executeUpdate();
    }

}
