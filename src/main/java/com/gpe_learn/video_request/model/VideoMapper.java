package com.gpe_learn.video_request.model;

import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public Video toUser(VideoDTO videoDTO) {
        return new Video(videoDTO.getTitle(), videoDTO.getLink());
    }
}
