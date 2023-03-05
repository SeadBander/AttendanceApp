package com.itacademy.AttendanceApp.entity;

import com.itacademy.AttendanceApp.repository.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    TimeEntryRepository timeEntryRepository;


 //   @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        Date now = new Date();
        String strtime = sdf.format(now);
        System.out.println("Java cron job expression:: " + strtime);
    }

//
//    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");


        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);

    }

}


