package com.itacademy.AttendanceApp.entity;

import org.springframework.scheduling.annotation.Scheduled;

public class CronJobSch {


    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void cronJobSch() throws Exception {
    }



}
