package com.ufes.acessousuarios.Util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateManipulation {

    public static LocalDate stringToLocalDate(String dateString) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    public static String localDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return null;
    }

}
