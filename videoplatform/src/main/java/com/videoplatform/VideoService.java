package com.videoplatform;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class VideoService {

    private final Connection connection;

    @Inject
    public VideoService(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();  // Створюємо таблицю, якщо її немає
    }

    // Метод для створення таблиці, якщо вона не існує
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS videos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);  // Виконання запиту для створення таблиці
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create table", e);
        }
    }

    // Метод для збереження відео в базі даних
    public void saveVideo(Video video) {
        String sql = "INSERT INTO videos (title) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, video.getTitle());
            statement.executeUpdate();  // Виконання запиту для вставки відео
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save video", e);
        }
    }
}
