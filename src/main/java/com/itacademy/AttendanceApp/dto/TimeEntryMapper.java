package com.itacademy.AttendanceApp.dto;

import com.itacademy.AttendanceApp.entity.TimeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TimeEntryMapper {


    @Mapping(target = "username", source = "user.username")
    TimeEntryResponseDto entityToResponseDto(TimeEntry entity);
}
