package org.example.Mapper;


import org.example.DTO.DoctorDTO;
import org.example.Model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);


    DoctorDTO toDocDto(Doctor doctor);

    Doctor toModel(DoctorDTO dto);
}
