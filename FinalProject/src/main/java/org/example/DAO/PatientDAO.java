package org.example.DAO;

import org.example.Model.Doctor;
import org.example.Model.Patient;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class PatientDAO implements Serializable {

    private static final String URL ="jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";
    private  static final String SELECT_ALL_PATIENT = "select * from PATIENTS";
    private static final String SELECT_ONE_PATIENT = "select * from PATIENTS where patient_id = ?";
    private static final String INSERT_PATIENT = "INSERT INTO PATIENTS (patient_name, patient_email, patient_password, patient_phone, patient_birth_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PATIENT = "update PATIENTS set patient_name = ?, patient_email = ? , patient_password = ? , patient_phone = ? , patient_birth_date = ?  where patient_id = ?";
    //    private static final String SELECT_DOCTOR_RATING = "select * from PATIENTS join CONSULTATIONS on PATIENTS.doctor_id = CONSULTATIONS.doctor_id where doctor_name = ?";
    private static final String DELETE_PATIENT= "delete from PATIENTS where patient_id = ?";




    public void INSERT_PATIENT (Patient patient) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_PATIENT);
        st.setString(1, patient.getPatient_name());
        st.setString(2, patient.getPatient_email());
        st.setString(3, patient.getPatient_password());
        st.setString(4, patient.getPatient_phone());
        st.setObject(5, patient.getPatient_birth_date().toString());
        st.executeUpdate();
    }

    public Patient SELECT_ONE_PATIENT(Integer patient_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_PATIENT);
        st.setInt(1, patient_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Patient(rs);
        }
        else {
            return null;
        }
    }


    public ArrayList<Patient> SELECT_ALL_PATIENT() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        st = conn.prepareStatement(SELECT_ALL_PATIENT);



        ResultSet rs = st.executeQuery();
        ArrayList<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add(new Patient(rs));
        }

        return patients;
    }
    public void UPDATE_PATIENT(Patient patient) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_PATIENT);
        st.setString(1, patient.getPatient_name());
        st.setString(2, patient.getPatient_email());
        st.setString(3, patient.getPatient_password());
        st.setString(4, patient.getPatient_phone());
        st.setObject(5, patient.getPatient_birth_date().toString());
        st.setInt(6, patient.getPatient_id());
        st.executeUpdate();
    }
}
