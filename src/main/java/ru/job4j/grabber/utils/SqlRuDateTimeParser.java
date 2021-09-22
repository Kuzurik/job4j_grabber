package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser{

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );

    @Override
    public LocalDateTime parse(String parse){
        String[] elements = parse.split(" ");
       for (String month : MONTHS.keySet()) {
           if (elements[1].equals(month)) {
               elements[1] = MONTHS.get(month);
           }
       }
       String[] year = elements[2].split(",");
       elements[2] = year[0];
       String date = String.format("%s-%s-%s %s",elements[0], elements[1], elements[2], elements[3]);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
       return LocalDateTime.parse(date, formatter);
    }
}
