package com.videoplatform;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.videoplatform.webserver.PayrollWebView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Модуль для конфігурації залежностей Guice для проекту
 */
public class VideoplatformModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).toInstance("YouTube Premier");
        bind(PaymentService.class).in(Singleton.class);
        bind(PayrollController.class).in(Singleton.class);
        bind(PayrollWebView.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public Connection provideConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:target/videoplatform.db");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    @Provides
    @Singleton
    public VideoService provideVideoService(Connection connection) {
        return new VideoService(connection);
    }
}
