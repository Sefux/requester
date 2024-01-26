package com.gpe_learn.video_request.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "batch.config")
public class ProcessBatchConfig {

    private Integer maxApiCalls = 2;
    private String maxResults = "5";

    private String searchTerm = "Klimawandel";
    private String nextPage = "";

    private Integer savedVideos = 0;

    private String part = "snippet";
    private String order = "viewCount";

    @Value("${rest.apikey}")
    private String apiKey; 
}
