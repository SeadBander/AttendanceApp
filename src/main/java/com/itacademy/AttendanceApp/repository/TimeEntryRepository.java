package com.itacademy.AttendanceApp.repository;


import com.itacademy.AttendanceApp.entity.TimeEntry;
import com.itacademy.AttendanceApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;


public interface TimeEntryRepository extends JpaRepository<TimeEntry,Integer>{
    List<TimeEntry> findAllByUser(User user);

    @Query("SELECT te from TimeEntry te WHERE te.user=:user ORDER BY te.clocksIn DESC LIMIT 1")
    TimeEntry findLatestByUser(User user);

    List<TimeEntry> findByUserAndClocksInBetween(User user, ZonedDateTime from, ZonedDateTime to);

}
