package com.gpe_learn.video_request.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoDTO {

    private String link;

    @JsonProperty("id")
    private void unpackNestedId(Map<String, Object> id) {
        this.link = (String) id.get("videoId");
    }

    private String title;

    @JsonProperty("snippet")
    private void unpackNestedSnippet(Map<String, Object> snippet) {
        this.title = (String) snippet.get("title");
    }

}
