package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {

    public static void main(String[] args) throws Exception {
        int page = 1;
        while (page < 6) {
            String url = String.format("https://www.sql.ru/forum/job-offers/%s", page);
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".altCol");
            for (Element td : row) {
                Element parent = td.parent();
                System.out.println(parent.children().get(5).text());
            }
            page++;
        }

    }
}
