package kz.danke.test.task.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class DateValidation {

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean isAgeValid(String date) {

        int minAge = 5;
        int maxAge = 150;

        LocalDate birthDate = LocalDate.parse(date, DATE_FORMATTER);

        return calculateAge(birthDate) >= minAge && calculateAge(birthDate) <= maxAge;
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
