package com.gpe_learn.video_request.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.gpe_learn.video_request.model.Video;;

public interface VideoRepository extends ListCrudRepository<Video, UUID> {

    Video findByLink(String link);
    
}
