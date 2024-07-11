package org.example.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PatientDTO {
    private Integer patient_id;
    private String patient_name ;
    private String patient_email;
    private String patient_password;
    private String patient_phone;
    private LocalDate patient_birth_date;
    private ArrayList<LinkDTO> links = new ArrayList<>();


    public PatientDTO() {

    }

    public PatientDTO(Integer patient_id,
                      String patient_name,
                      String patient_email,
                      String patient_password,
                      String patient_phone,
                      LocalDate patient_birth_date,
                      ArrayList<LinkDTO> links) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_password = patient_password;
        this.patient_phone = patient_phone;
        this.patient_birth_date = patient_birth_date;
        this.links = links;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public LocalDate getPatient_birth_date() {
        return patient_birth_date;
    }

    public void setPatient_birth_date(LocalDate patient_birth_date) {
        this.patient_birth_date = patient_birth_date;
    }

    public ArrayList<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_email='" + patient_email + '\'' +
                ", patient_password='" + patient_password + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", patient_birth_date=" + patient_birth_date +
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
