package org.example.DAO;


import org.example.Model.Consultation;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class ConsultationDAO implements Serializable  {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";
    private  static final String SELECT_ALL_CONSULTATIONS = "select * from CONSULTATIONS";
    private static final String SELECT_ONE_CONSULTATIONS = "select * from CONSULTATIONS where consultation_id = ?";
    private static final String INSERT_CONSULTATIONS = "INSERT INTO CONSULTATIONS (doctor_id, patient_id, consultation_request_time, consultation_time, consultation_status, consultation_diagnosis, rating) VALUES (?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_CONSULTATIONS = "update CONSULTATIONS set doctor_id = ?, patient_id = ?, consultation_request_time = ? , consultation_time = ? , consultation_status = ? , consultation_diagnosis = ? , rating =?  where consultation_id = ?";
    private static final String DELETE_CONSULTATIONS = "delete from CONSULTATIONS where consultation_id = ?";



    public void INSERT_CONSULTATIONS (Consultation consultation) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_CONSULTATIONS);
        st.setInt(1, consultation.getPatient_id());
        st.setInt(2, consultation.getDoctor_id());
        st.setObject(3, consultation.getConsultation_request_time().toString());
        st.setString(4, consultation.getConsultation_time().toString());
        st.setString(5, consultation.getConsultation_status());
        st.setString(6, consultation.getConsultation_diagnosis());
        st.setInt(7, consultation.getRating());
        st.executeUpdate();
    }

    public Consultation SELECT_ONE_CONSULTATIONS(Integer consultation_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_CONSULTATIONS);
        st.setInt(1, consultation_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Consultation(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Consultation> SELECT_ALL_CONSULTATIONS() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        st = conn.prepareStatement(SELECT_ALL_CONSULTATIONS);



        ResultSet rs = st.executeQuery();
        ArrayList<Consultation> consultations = new ArrayList<>();
        while (rs.next()) {
            consultations.add(new Consultation(rs));
        }

        return consultations;
    }

    public void UPDATE_CONSULTATIONS(Consultation consultation) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_CONSULTATIONS);
        st.setInt(1, consultation.getDoctor_id());
        st.setInt(2, consultation.getPatient_id());
        st.setObject(3, consultation.getConsultation_request_time().toString());
        st.setString(4, consultation.getConsultation_time().toString());
        st.setString(5, consultation.getConsultation_status());
        st.setString(6, consultation.getConsultation_diagnosis());
        st.setInt(7, consultation.getRating());
        st.setInt(8, consultation.getConsultation_id());
        st.executeUpdate();
    }
}
