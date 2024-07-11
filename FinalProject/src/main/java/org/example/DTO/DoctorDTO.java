package org.example.DTO;

import java.util.ArrayList;

public class DoctorDTO {

    private Integer doctor_id;
    private String doctor_name ;
    private String doctor_specialty;
    private String doctor_email;
    private String doctor_password;
    private String doctor_phone;
    private ArrayList<LinkDTO> links = new ArrayList<>();


    public DoctorDTO(){

    }
    public DoctorDTO(Integer doctor_id,
                     String doctor_name,
                     String doctor_specialty,
                     String doctor_email,
                     String doctor_phone,
                     String doctor_password) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_specialty = doctor_specialty;
        this.doctor_email = doctor_email;
        this.doctor_phone = doctor_phone;
        this.doctor_password = doctor_password;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_specialty() {
        return doctor_specialty;
    }

    public void setDoctor_specialty(String doctor_specialty) {
        this.doctor_specialty = doctor_specialty;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", doctor_specialty='" + doctor_specialty + '\'' +
                ", doctor_email='" + doctor_email + '\'' +
                ", doctor_password='" + doctor_password + '\'' +
                ", doctor_phone='" + doctor_phone + '\'' +
                '}';
    }

    public void addLink(String url, String rel) {
        LinkDTO link = new LinkDTO();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }


}
