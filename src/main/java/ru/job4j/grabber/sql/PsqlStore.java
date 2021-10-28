package ru.job4j.grabber.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private final Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement("insert into post(title, link, description, dateTime) values (?, ?, ?, ?)")) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getLink());
            ps.setString(3, post.getDescription());
            ps.setObject(4, post.getDateTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (PreparedStatement st = cnn.prepareStatement("SELECT * From post")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Post(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("link"),
                        rs.getString("description"),
                        rs.getObject(5, LocalDateTime.class)
                        ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Post findById(int id) {
        Post result = null;
        try (PreparedStatement st = cnn.prepareStatement("select * from post as i where i.id in (?)")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new Post(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("link"),
                        rs.getString("description"),
                        rs.getObject(5, LocalDateTime.class
                        ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws ParseException, IOException {
        Properties cfg = new Properties();
        try (InputStream in = SqlRuParse.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PsqlStore psqlStore = new PsqlStore(cfg);
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> posts = sqlRuParse.list("https://www.sql.ru/forum/job-offers/1");
        posts.forEach(psqlStore::save);
        psqlStore.getAll().forEach(System.out::println);
        System.out.println(psqlStore.findById(21));
    }
}
