package com.itacademy.AttendanceApp.service;

import com.itacademy.AttendanceApp.entity.TimeEntry;
import com.itacademy.AttendanceApp.entity.User;
import com.itacademy.AttendanceApp.exception.ClockOutException;
import com.itacademy.AttendanceApp.repository.TimeEntryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
public class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;

    public List<TimeEntry> getAllTimeEntry() {
        return timeEntryRepository.findAll();
    }

    public TimeEntry clockIn(User user) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setUser(user);
        timeEntry.setClocksIn(ZonedDateTime.now());
        return timeEntryRepository.save(timeEntry);
    }

    public TimeEntry clockOut(User user) {
        TimeEntry timeEntry = timeEntryRepository.findLatestByUser(user);
        if (timeEntry.getClocksOut() != null) {
            throw new ClockOutException("You need to clock IN first");
        }
        timeEntry.setClocksOut(ZonedDateTime.now());
        return timeEntryRepository.save(timeEntry);
    }


    public List<TimeEntry> getByUser(User user) {
        return timeEntryRepository.findAllByUser(user);
    }

    public List<TimeEntry> getByUserInPeriod(User user, ZonedDateTime from, ZonedDateTime to) {
        return timeEntryRepository.findByUserAndClocksInBetween(user, from, to);
    }


    public String deleteRow(Integer id) {
        timeEntryRepository.deleteById(id);
        return "Success";
    }

    public void save(TimeEntry timeEntry) {
        timeEntryRepository.save(timeEntry);
    }


}










