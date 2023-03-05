package com.itacademy.AttendanceApp.security;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;

import java.util.Base64;

@UtilityClass
public class SecurityUtils {
    public static String getUsername(String authorization) {
        authorization = authorization.replace("Basic ", Strings.EMPTY).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(authorization);
        String decodedString = new String(decodedBytes);
        return decodedString.split(":")[0];
    }
}
