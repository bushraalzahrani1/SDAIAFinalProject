package org.example.Mapper;

import org.example.DTO.MedicalReportDTO;
import org.example.Model.MedicalReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicalReportMapper {

    MedicalReportMapper INSTANCE = Mappers.getMapper(MedicalReportMapper.class);


    MedicalReportDTO toMedDto(MedicalReport schedule);

    MedicalReport toModel(MedicalReportDTO dto);
}
