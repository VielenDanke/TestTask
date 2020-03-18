package kz.danke.test.task.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateValidation {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean isAgeValid(String date) {

        int minAge = 5;
        int maxAge = 150;

        LocalDate birthDate = LocalDate.parse(date, DATE_FORMATTER);

        return calculateAge(birthDate) >= minAge && calculateAge(birthDate) <= maxAge;
    }

    private static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
