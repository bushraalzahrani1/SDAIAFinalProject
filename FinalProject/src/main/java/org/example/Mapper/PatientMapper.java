package org.example.Mapper;


import org.example.DTO.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);


    PatientDTO toPatDto(Patient doctor);

    Patient toModel(PatientDTO dto);

}
