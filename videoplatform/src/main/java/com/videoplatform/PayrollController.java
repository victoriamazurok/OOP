package com.videoplatform;

import com.google.inject.Inject;
import java.util.List;

public class PayrollController {

    private final VideoService videoService;

    @Inject
    public PayrollController(VideoService videoService) {
        this.videoService = videoService;
    }

    public List<Paycheck> getPaychecks() {
        return videoService.getPaychecks();  // Замінили getAllPaychecks() на getPaychecks()
    }
}
