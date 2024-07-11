package org.example.DTO;

import jakarta.ws.rs.QueryParam;

public class DoctorFilterDTO {

    private @QueryParam("doctor_name") String doctor_name;
    private @QueryParam("doctor_specialty") String doctor_specialty;


    public DoctorFilterDTO() {

    }

    public DoctorFilterDTO(String doctor_name, String doctor_specialty) {
        this.doctor_name = doctor_name;
        this.doctor_specialty = doctor_specialty;
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

    @Override
    public String toString() {
        return "DoctorFilterDTO{" +
                "doctor_name='" + doctor_name + '\'' +
                ", doctor_specialty='" + doctor_specialty + '\'' +
                '}';
    }
}
