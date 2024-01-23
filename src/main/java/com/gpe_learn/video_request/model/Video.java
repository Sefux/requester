package com.gpe_learn.video_request.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String link;

    public Video() {
    }

    public Video(String title, String link) {
        this.title = title;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Video [id=" + id + ", title=" + title + ", link=" + link + "]";
    }

}
