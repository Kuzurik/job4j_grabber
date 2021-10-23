package ru.job4j.grabber.utils;

import java.time.LocalDate;
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

    public String today(String time) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");
        String dateFormat = date.format(format);
        return String.format("%s %s",dateFormat, time);
    }

    @Override
    public LocalDateTime parse(String parse){
        String[] elements = parse.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy HH:mm");
        if (elements[0].equals("сегодня,")) {
            return LocalDateTime.parse(today(elements[1]),formatter);
        }
        if (elements[0].equals("вчера,")) {
            return LocalDateTime.parse(today(elements[1]),formatter).minusDays(1);
        }
        String[] year = elements[2].split(",");
        String date = String.format("%s-%s-%s %s",elements[0], MONTHS.get(elements[1]), year[0], elements[3]);
        return LocalDateTime.parse(date, formatter);
    }
}
