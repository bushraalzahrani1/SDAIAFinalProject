package org.example.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MedicalReportDTO {

    private Integer medical_report_id;
    private Integer patient_id;
    private String medical_report_details;
    private LocalDateTime medical_report_date;
    private ArrayList<LinkDTO> links = new ArrayList<>();

    public MedicalReportDTO() {

    }

    public MedicalReportDTO(Integer medical_report_id, Integer patient_id, String medical_report_details, LocalDateTime medical_report_date, ArrayList<LinkDTO> links) {
        this.medical_report_id = medical_report_id;
        this.patient_id = patient_id;
        this.medical_report_details = medical_report_details;
        this.medical_report_date = medical_report_date;
        this.links = links;
    }

    public Integer getMedical_report_id() {
        return medical_report_id;
    }

    public void setMedical_report_id(Integer medical_report_id) {
        this.medical_report_id = medical_report_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedical_report_details() {
        return medical_report_details;
    }

    public void setMedical_report_details(String medical_report_details) {
        this.medical_report_details = medical_report_details;
    }

    public LocalDateTime getMedical_report_date() {
        return medical_report_date;
    }

    public void setMedical_report_date(LocalDateTime medical_report_date) {
        this.medical_report_date = medical_report_date;
    }

    public ArrayList<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "MedicalReportDTO{" +
                "medical_report_id=" + medical_report_id +
                ", patient_id=" + patient_id +
                ", medical_report_details='" + medical_report_details + '\'' +
                ", medical_report_date=" + medical_report_date +
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
