package com.videoplatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoService {

    private final Connection connection;

    public VideoService(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS paychecks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "amount REAL NOT NULL" +
                ");";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create table", e);
        }
    }

    public List<Paycheck> getPaychecks() {
        // Example hardcoded paychecks for now
        List<Paycheck> paychecks = new ArrayList<>();
        paychecks.add(new Paycheck("John Doe", 5000.0));
        paychecks.add(new Paycheck("Jane Smith", 5500.0));
        return paychecks;
    }

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
