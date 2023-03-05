package com.itacademy.AttendanceApp.controller;

public class Endpoint {
    public static final String USER = "/user";
    public static final String TIME = "/time";
    public static final String REPORT = "/report";

    public static final String[] NON_PUBLIC_ENDPOINTS = {
            Endpoint.USER + "/**",
            Endpoint.TIME + "/**",
            Endpoint.REPORT + "/**"
    };

    public static final String[] PUBLIC_ENDPOINTS = {
            Endpoint.USER + "/register",
    };
}
