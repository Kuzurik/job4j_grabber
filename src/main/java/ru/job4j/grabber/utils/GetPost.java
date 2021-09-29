package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class GetPost {

    public static final int DATE_SIZE = 5;

    public String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgBody");
        return row.next().text();
    }

    public LocalDateTime getDate(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgFooter");
        String[] date = row.first().text().split(" ");
        String parseDate = String.join(" ", Arrays.copyOf(date, DATE_SIZE));
        return new SqlRuDateTimeParser().parse(parseDate);
    }
}
