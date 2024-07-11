package org.example.DAO;


import org.example.Model.Schedule;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class ScheduleDAO implements Serializable {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\FinalProject\\FinalProject4.db";
    private  static final String SELECT_ALL_SCHEDULE = "select * from SCHEDULES";
    private static final String SELECT_ONE_SCHEDULE = "select * from SCHEDULES where schedule_id = ?";
    private static final String INSERT_SCHEDULE = "INSERT INTO SCHEDULES (doctor_id, schedule_start_time, schedule_end_time, schedule_is_available) VALUES (?, ?, ?,?) ";
    private static final String UPDATE_SCHEDULE = "update SCHEDULES set doctor_id = ?, schedule_start_time = ? , schedule_end_time = ? , schedule_is_available = ?  where schedule_id = ?";
    private static final String DELETE_SCHEDULES= "delete from SCHEDULES where schedule_id = ?";



    public void INSERT_SCHEDULE (Schedule schedule) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_SCHEDULE);
        st.setInt(1, schedule.getDoctor_id());
        st.setObject(2, schedule.getSchedule_start_time().toString());
        st.setObject(3, schedule.getSchedule_end_time().toString());
        st.setBoolean(4, schedule.getSchedule_is_available());
        st.executeUpdate();
    }

    public Schedule SELECT_ONE_SCHEDULE(Integer schedule_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_SCHEDULE);
        st.setInt(1, schedule_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Schedule(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Schedule> SELECT_ALL_SCHEDULE() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        st = conn.prepareStatement(SELECT_ALL_SCHEDULE);



        ResultSet rs = st.executeQuery();
        ArrayList<Schedule> schedule = new ArrayList<>();
        while (rs.next()) {
            schedule.add(new Schedule(rs));
        }

        return schedule;
    }

    public void UPDATE_SCHEDULE(Schedule schedule) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_SCHEDULE);
        st.setInt(1, schedule.getDoctor_id());
        st.setObject(2, schedule.getSchedule_start_time().toString());
        st.setObject(3, schedule.getSchedule_end_time().toString());
        st.setBoolean(4, schedule.getSchedule_is_available());
        st.setInt(5, schedule.getSchedule_id());
        st.executeUpdate();
    }
}
