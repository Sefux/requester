package com.gpe_learn.video_request.controller;

import com.gpe_learn.video_request.model.Video;
import com.gpe_learn.video_request.service.VideoService;

import java.util.List;
import java.util.UUID;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    
    private VideoService videoService;

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable UUID id) {
        return videoService.getVideoById(id);
    }

    @PostMapping
    public Video addVideo(@RequestBody Video video) {
        return videoService.addVideo(video);
    }

    @PutMapping("/{id}")
    public Video updateVideo(@PathVariable UUID id, @RequestBody Video video) {
        return videoService.updateVideo(id, video);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable UUID id) {
        videoService.deleteVideo(id);
    }
}
