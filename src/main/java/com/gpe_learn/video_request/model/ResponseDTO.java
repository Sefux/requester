package com.gpe_learn.video_request.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("nextPageToken")
    private String nextPageToken;

    @JsonProperty("items")
    private List<VideoDTO> items;

}
