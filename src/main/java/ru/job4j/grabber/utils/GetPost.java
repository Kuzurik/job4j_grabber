package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;

public class GetPost {

    public String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgBody");
        return row.next().text();
    }

    public LocalDateTime getDate(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgFooter");
        String[] date = row.first().text().split(" ");
        StringBuilder parseDate = new StringBuilder();
        int index = 0;
        while (index < 5){
           parseDate.append(date[index]).append(" ");
           index++;
        }
        return new SqlRuDateTimeParser().parse(parseDate.toString());
    }
}
