package com.videoplatform.webserver;

import io.javalin.Javalin;
import com.videoplatform.VideoService;

public class JavalinWebServer {

    private final VideoService videoService;

    public JavalinWebServer(VideoService videoService) {
        this.videoService = videoService;
    }

    public void start() {
        Javalin app = Javalin.create().start(7000);
        app.get("/paychecks", ctx -> {
            ctx.json(videoService.getPaychecks());
        });
    }
}
