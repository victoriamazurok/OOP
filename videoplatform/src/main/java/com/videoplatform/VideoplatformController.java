package com.videoplatform;

import com.google.inject.Inject;
import java.util.List;

/**
 * Контролер для управління відео
 * Обробляє запити від вигляду та взаємодіє з сервісом
 */
public class VideoplatformController {

    private final VideoService videoService;

    @Inject
    public VideoplatformController(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * Отримує всі видео з бази даних
     * @return список всіх відео
     */
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }
}
