package com.itacademy.AttendanceApp.controller;

import com.itacademy.AttendanceApp.dto.TimeEntryMapper;
import com.itacademy.AttendanceApp.dto.TimeEntryResponseDto;
import com.itacademy.AttendanceApp.entity.TimeEntry;
import com.itacademy.AttendanceApp.entity.User;
import com.itacademy.AttendanceApp.security.SecurityUtils;
import com.itacademy.AttendanceApp.service.TimeEntryService;
import com.itacademy.AttendanceApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@RestController
@RequestMapping(Endpoint.TIME)
@RequiredArgsConstructor
public class TimeEntryController {

    private final TimeEntryService timeEntryService;
    private final UserService userService;
    private final TimeEntryMapper entryMapper;

    @PostMapping("/in/{userId}")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<TimeEntryResponseDto> clockIn(@PathVariable("userId") Long userId) throws IOException {
        User user = userService.findById(userId);
        TimeEntry newTimeEntry = timeEntryService.clockIn(user);
        return new ResponseEntity<>(entryMapper.entityToResponseDto(newTimeEntry), HttpStatus.CREATED);
    }

    @PostMapping("/out/{userId}")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<TimeEntryResponseDto> clockOut(@PathVariable("userId") Long userId) throws IOException {
        User user = userService.findById(userId);
        TimeEntry newTimeEntry = timeEntryService.clockOut(user);
        return new ResponseEntity<>(entryMapper.entityToResponseDto(newTimeEntry), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<TimeEntryResponseDto>> getAllByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        List<TimeEntry> resultsList = timeEntryService.getByUser(user);
        List<TimeEntryResponseDto> resultDtoList = resultsList.stream()
                .map(entryMapper::entityToResponseDto)
                .toList();
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user','admin')")
    public ResponseEntity<List<TimeEntryResponseDto>> getAllByAuthenticatedUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        String username = SecurityUtils.getUsername(authorization);
        User user = userService.findByUsername(username);
        List<TimeEntry> resultsList = timeEntryService.getByUser(user);
        List<TimeEntryResponseDto> resultDtoList = resultsList.stream()
                .map(entryMapper::entityToResponseDto)
                .toList();
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

    @GetMapping("/period/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<TimeEntryResponseDto>> getAllInPeriodForUser(
            @PathVariable("username") String username,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime to
    ) {
        ZonedDateTime fromTime = from.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime toTime = to.withZoneSameInstant(ZoneId.of("UTC"));
        User user = userService.findByUsername(username);
        List<TimeEntry> resultsList = timeEntryService.getByUserInPeriod(user, fromTime, toTime);
        List<TimeEntryResponseDto> resultDtoList = resultsList.stream().map(entryMapper::entityToResponseDto).toList();
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

}



