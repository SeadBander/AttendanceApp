package com.itacademy.AttendanceApp.exception;

import jakarta.validation.ValidationException;

public class ClockOutException extends ValidationException {
    public ClockOutException(String message) {
        super(message);
    }
}
