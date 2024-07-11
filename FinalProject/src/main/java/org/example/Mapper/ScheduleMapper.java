package org.example.Mapper;

import org.example.DTO.ScheduleDTO;
import org.example.Model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);


    ScheduleDTO toSecDto(Schedule schedule);

    Schedule toModel(ScheduleDTO dto);
}
