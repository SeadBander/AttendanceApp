package com.itacademy.AttendanceApp.controller;

import com.itacademy.AttendanceApp.entity.TimeEntry;
import com.itacademy.AttendanceApp.entity.User;
import com.itacademy.AttendanceApp.generate.PDFGenerator;
import com.itacademy.AttendanceApp.service.TimeEntryService;
import com.itacademy.AttendanceApp.service.UserService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.REPORT)
public class PDFController {

    private final TimeEntryService timeEntryService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public void generatePdf(
            HttpServletResponse response,
            @RequestParam("username") String username,
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime to) throws DocumentException, IOException {
        User user = userService.findByUsername(username);
        List<TimeEntry> timeEntryList;

        if (from != null && to != null) {
            ZonedDateTime fromTime = from.withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime toTime = to.withZoneSameInstant(ZoneId.of("UTC"));
            timeEntryList = timeEntryService.getByUserInPeriod(user, fromTime, toTime);
        } else {
            timeEntryList = timeEntryService.getAllTimeEntry();
        }

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content_Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        PDFGenerator generator = new PDFGenerator(timeEntryList);
        generator.generate(response);
    }


}
