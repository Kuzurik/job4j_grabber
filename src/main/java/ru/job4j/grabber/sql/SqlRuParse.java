package ru.job4j.grabber.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse{

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws ParseException, IOException {
        List<Post> result = new ArrayList<>();
        Document doc = Jsoup.connect(link).get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element href = td.child(0);
            String url = href.attr("href");
            if (!td.text().contains("Важно:")) {
                result.add(detail(url));
            }

        }
        return result;
    }

    @Override
    public Post detail(String link) throws ParseException, IOException {
        Document document = Jsoup.connect(link).get();
        Elements rowTitle = document.select(".messageHeader");
        String title = rowTitle.first().text();
        Elements rowDes = document.select(".msgBody");
        int indexDes = 1;
        String des = rowDes.get(indexDes).text();
        Elements rowDate = document.select(".msgFooter");
        String date = rowDate.first().text();
        LocalDateTime create = dateTimeParser.parse(date.substring(0, date.indexOf(":") + 3));
        return new Post(title, link, des, create);
    }
}
