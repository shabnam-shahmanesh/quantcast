package com.quantcast.assignment.util;

import com.quantcast.assignment.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * A <code>DateUtil</code>
 * provide date utilities
 *
 * @author Shabnam Shahmanesh
 */
public class DateUtil {

    private DateUtil() {

    }

    /**
     * Check if the date string is in correct format
     *
     * @param dateString   A string containing date
     * @return boolean
     */
    public static boolean isValidDate(String dateString) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
            LocalDate parsedDate = LocalDate.parse(dateString, dateFormatter);
            return Objects.nonNull(parsedDate);
        } catch(DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
