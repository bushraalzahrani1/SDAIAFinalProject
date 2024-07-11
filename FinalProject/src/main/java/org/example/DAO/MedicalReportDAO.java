package org.example.DAO;

import org.example.Model.MedicalReport;



import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class MedicalReportDAO  implements Serializable {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";
    private  static final String SELECT_ALL_MEDICAL_REPORTS = "select * from MEDICAL_REPORTS";
    private static final String SELECT_ONE_MEDICAL_REPORT = "select * from MEDICAL_REPORTS where medical_report_id = ?";
    private static final String INSERT_MEDICAL_REPORTS = "INSERT INTO MEDICAL_REPORTS (patient_id, medical_report_details, medical_report_date) VALUES (?, ?, ?) ";
    private static final String UPDATE_MEDICAL_REPORTS = "update MEDICAL_REPORTS set patient_id = ?, medical_report_details = ? , medical_report_date = ?   where medical_report_id = ?";
    private static final String DELETE_MEDICAL_REPORTS = "delete from MEDICAL_REPORTS where medical_report_id = ?";



    public void INSERT_MEDICAL_REPORTS (MedicalReport medicalReport) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_MEDICAL_REPORTS);
        st.setInt(1, medicalReport.getPatient_id());
        st.setString(2, medicalReport.getMedical_report_details());
        st.setObject(3, medicalReport.getMedical_report_date().toString());
        st.executeUpdate();
    }

    public MedicalReport SELECT_ONE_MEDICAL_REPORT(Integer medical_report_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_MEDICAL_REPORT);
        st.setInt(1, medical_report_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new MedicalReport(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<MedicalReport> SELECT_ALL_MEDICAL_REPORTS() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        st = conn.prepareStatement(SELECT_ALL_MEDICAL_REPORTS);



        ResultSet rs = st.executeQuery();
        ArrayList<MedicalReport> medicalReport = new ArrayList<>();
        while (rs.next()) {
            medicalReport.add(new MedicalReport(rs));
        }

        return medicalReport;
    }


    public void UPDATE_MEDICAL_REPORTS(MedicalReport medicalReport) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_MEDICAL_REPORTS);
        st.setInt(1, medicalReport.getPatient_id());
        st.setString(2, medicalReport.getMedical_report_details());
        st.setObject(3, medicalReport.getMedical_report_date().toString());
        st.setInt(4, medicalReport.getMedical_report_id());

        st.executeUpdate();
    }
}
