package com.itacademy.AttendanceApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
public class TimeEntryResponseDto {

    private ZonedDateTime clocksIn;

    private ZonedDateTime clocksOut;

    private String username;

}
