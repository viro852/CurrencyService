package com.example.springgradleexample.prototype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePrototype {

    public static String today(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = dateTimeFormatter.format(LocalDate.now());
        return today;
    }

    public static String yesterday(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterday = dateTimeFormatter.format(LocalDate.now().minusDays(1));
        return yesterday;
    }
}
