package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {

    private int id;
    private final String title;
    private final String link;
    private final String description;
    private final LocalDateTime dateTime;

    public Post(String title, String link, String description, LocalDateTime created) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.dateTime = created;
    }

    public Post(int id, String title, String link, String description, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(title, post.title)
                && Objects.equals(link, post.link)
                && Objects.equals(dateTime, post.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, dateTime);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", dateTime=" + dateTime
                + '}';
    }
}
