package com.videoplatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервіс для роботи з відео з бази даних
 */
public class VideoService {

    private final Connection connection;

    public VideoService(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }

    /**
     * Створює таблицю videos якщо її немає
     */
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS videos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL" +
                ");";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
            // Додаємо приклади видео якщо таблиця нова
            insertDefaultVideos();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create table", e);
        }
    }

    /**
     * Додає приклади видео в таблицю
     */
    private void insertDefaultVideos() {
        try {
            // Перевіряємо чи таблиця порожня
            String countSQL = "SELECT COUNT(*) FROM videos";
            try (PreparedStatement stmt = connection.prepareStatement(countSQL);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    // Додаємо приклади
                    addDefaultVideos();
                }
            }
        } catch (SQLException e) {
            // Ігноруємо помилку
        }
    }

    /**
     * Додає приклади видео
     */
    private void addDefaultVideos() {
        String[] videoTitles = {
                "Як створити вебсайт за 10 хвилин",
                "Java для починаючих розробників",
                "Основи баз даних SQLite",
                "REST API з Javalin фреймворком",
                "Паттерни проєктування в Java"
        };
        
        String insertSQL = "INSERT INTO videos (title) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
            for (String title : videoTitles) {
                stmt.setString(1, title);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            // Ігноруємо якщо не вдалося додати
        }
    }

    /**
     * Отримує всі видео з бази даних
     * @return список видео
     */
    public List<Video> getAllVideos() {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT id, title FROM videos ORDER BY id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Video video = new Video(title);
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    /**
     * Зберігає видео в БД
     * @param video видео для збереження
     */
    public void saveVideo(Video video) {
        String sql = "INSERT INTO videos (title) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, video.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save video", e);
        }
    }
}
