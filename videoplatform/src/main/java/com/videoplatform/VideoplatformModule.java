package com.videoplatform;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VideoplatformModule extends AbstractModule {

    @Override
    protected void configure() {
        // Зв'язок для інжекції залежності
        bind(String.class).toInstance("YouTube Premier");  // Налаштовуємо systemName
    }

    @Provides
    @Singleton
    public Connection provideConnection() throws SQLException {
        // Підключення до бази даних
        return DriverManager.getConnection("jdbc:sqlite:target/videoplatform.db");
    }

    @Provides
    @Singleton
    public VideoService provideVideoService(Connection connection) {
        // Створення сервісу для збереження відео
        return new VideoService(connection);
    }
}
