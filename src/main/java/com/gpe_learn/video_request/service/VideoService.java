package com.gpe_learn.video_request.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gpe_learn.video_request.model.Video;
import com.gpe_learn.video_request.repository.VideoRepository;
import com.gpe_learn.video_request.exception.VideoDoesNotExistException;

@Service
public class VideoService {

    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video getVideoById(UUID id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoDoesNotExistException(id));
    }

    public Video addVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video updateVideo(UUID id, Video video) {
        getVideoById(id); // Check if the video exists
        video.setId(id);
        return videoRepository.save(video);
    }

    public void deleteVideo(UUID id) {
        getVideoById(id); // Check if the video exists
        videoRepository.deleteById(id);
    }
}