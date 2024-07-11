package org.example.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ScheduleDTO {

    private Integer schedule_id;
    private Integer doctor_id;
    private LocalDateTime schedule_start_time;
    private LocalDateTime schedule_date_time;
    private boolean schedule_is_available;
    private ArrayList<LinkDTO> links = new ArrayList<>();

    public ScheduleDTO() {

    }

    public ScheduleDTO(Integer schedule_id,
                       Integer doctor_id,
                       LocalDateTime schedule_start_time,
                       LocalDateTime schedule_date_time,
                       boolean schedule_is_available,
                       ArrayList<LinkDTO> links) {
        this.schedule_id = schedule_id;
        this.doctor_id = doctor_id;
        this.schedule_start_time = schedule_start_time;
        this.schedule_date_time = schedule_date_time;
        this.schedule_is_available = schedule_is_available;
        this.links = links;
    }

    public Integer getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Integer schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public LocalDateTime getSchedule_start_time() {
        return schedule_start_time;
    }

    public void setSchedule_start_time(LocalDateTime schedule_start_time) {
        this.schedule_start_time = schedule_start_time;
    }

    public LocalDateTime getSchedule_date_time() {
        return schedule_date_time;
    }

    public void setSchedule_date_time(LocalDateTime schedule_date_time) {
        this.schedule_date_time = schedule_date_time;
    }

    public boolean isSchedule_is_available() {
        return schedule_is_available;
    }

    public void setSchedule_is_available(boolean schedule_is_available) {
        this.schedule_is_available = schedule_is_available;
    }

    public ArrayList<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "schedule_id=" + schedule_id +
                ", doctor_id=" + doctor_id +
                ", schedule_start_time=" + schedule_start_time +
                ", schedule_date_time=" + schedule_date_time +
                ", schedule_is_available=" + schedule_is_available +
                ", links=" + links +
                '}';
    }

    public void addLink(String url, String rel) {
        LinkDTO link = new LinkDTO();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

}
