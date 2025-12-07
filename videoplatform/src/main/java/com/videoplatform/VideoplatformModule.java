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
        bind(String.class).toInstance("YouTube Premier");
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

    @Provides
    @Singleton
    public JavalinWebServer provideWebServer() {
        return new JavalinWebServer(provideVideoService(provideConnection()));
    }

    @Provides
    @Singleton
    public PayrollWebView providePayrollWebView() {
        return new PayrollWebView(provideVideoService(provideConnection()));
    }
}
