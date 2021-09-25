package ru.job4j.grabber.utils;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SqlRuDateTimeParserTest {

    @Test
    public void whenParseDate() {
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        String date = "10 сен 21, 15:07";
        LocalDateTime result = parser.parse(date);
        String expectedDate = "10-09-21 15:07";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
        LocalDateTime expected = LocalDateTime.parse(expectedDate, formatter);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParseDateToday() {
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        String date = "сегодня, 15:07";
        LocalDateTime result = parser.parse(date);
        String expectedDate = "25-09-21 15:07";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
        LocalDateTime expected = LocalDateTime.parse(expectedDate, formatter);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParseDateYesterday() {
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        String date = "вчера, 15:07";
        LocalDateTime result = parser.parse(date);
        String expectedDate = "24-09-21 15:07";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
        LocalDateTime expected = LocalDateTime.parse(expectedDate, formatter);
        assertThat(result, is(expected));
    }
}