package com.videoplatform;
import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VideoService {

    private final Connection connection;

    @Inject
    public VideoService(Connection connection) {
        this.connection = connection;
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
