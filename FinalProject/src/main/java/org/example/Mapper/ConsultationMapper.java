package org.example.Mapper;


import org.example.DTO.ConsultationDTO;
import org.example.Model.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);


    ConsultationDTO toConDto(Consultation consultation);

    Consultation toModel(ConsultationDTO dto);
}
