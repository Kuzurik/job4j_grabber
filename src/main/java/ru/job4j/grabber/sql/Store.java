package ru.job4j.grabber.sql;

import java.util.List;

public interface Store {

    void save(Post post);

    List<Post> getAll();

    Post findById(int id);
}
