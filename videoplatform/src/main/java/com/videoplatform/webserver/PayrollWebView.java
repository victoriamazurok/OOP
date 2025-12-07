package com.videoplatform.webserver;

import com.videoplatform.VideoService;
import com.videoplatform.Paycheck;
import java.util.List;

public class PayrollWebView {

    private final VideoService videoService;

    public PayrollWebView(VideoService videoService) {
        this.videoService = videoService;
    }

    public List<Paycheck> getPaychecks() {
        return videoService.getPaychecks();
    }

    public void start(int port) {
        // Реалізація методу start, якщо він потрібен для сервера
    }
}
