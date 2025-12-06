package com.videoplatform;
import com.google.inject.Inject;


public class Video {
    private String title;

    @Inject
    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}