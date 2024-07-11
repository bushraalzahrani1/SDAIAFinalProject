package org.example.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsultationDTO {

    private Integer consultation_id;
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDateTime consultation_request_time;
    private LocalDateTime consultation_time;
    private String consultation_status;
    private String consultation_diagnosis;
    private Integer rating;
    private ArrayList<LinkDTO> links = new ArrayList<>();

    public ConsultationDTO() {

    }

    public ConsultationDTO(Integer consultation_id,
                           Integer doctor_id,
                           Integer patient_id,
                           LocalDateTime consultation_request_time,
                           LocalDateTime consultation_time,
                           String consultation_status,
                           String consultation_diagnosis,Integer rating) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.consultation_request_time = consultation_request_time;
        this.consultation_time = consultation_time;
        this.consultation_status = consultation_status;
        this.consultation_diagnosis = consultation_diagnosis;
        this.rating = rating;
    }

    public Integer getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(Integer consultation_id) {
        this.consultation_id = consultation_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDateTime getConsultation_request_time() {
        return consultation_request_time;
    }

    public void setConsultation_request_time(LocalDateTime consultation_request_time) {
        this.consultation_request_time = consultation_request_time;
    }

    public LocalDateTime getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(LocalDateTime consultation_time) {
        this.consultation_time = consultation_time;
    }

    public String getConsultation_status() {
        return consultation_status;
    }

    public void setConsultation_status(String consultation_status) {
        this.consultation_status = consultation_status;
    }

    public String getConsultation_diagnosis() {
        return consultation_diagnosis;
    }

    public void setConsultation_diagnosis(String consultation_diagnosis) {
        this.consultation_diagnosis = consultation_diagnosis;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ConsultationDTO{" +
                "consultation_id=" + consultation_id +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                ", consultation_request_time=" + consultation_request_time +
                ", consultation_time=" + consultation_time +
                ", consultation_status='" + consultation_status + '\'' +
                ", consultation_diagnosis='" + consultation_diagnosis + '\'' +
                ", rating=" + rating +
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