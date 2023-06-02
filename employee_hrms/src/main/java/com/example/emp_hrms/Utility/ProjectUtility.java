package com.example.emp_hrms.Utility;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectUtility {
    public static LocalDate getCurrentTimeStamp() {
        return LocalDate.now();
    }

    public static boolean validateEmail(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
