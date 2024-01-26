package com.gpe_learn.video_request.service;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.gpe_learn.video_request.config.ProcessBatchConfig;
import com.gpe_learn.video_request.model.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Service
public class YoutubeApiRestService {

	private WebClient webClient;

	private ProcessBatchConfig processBatchConfig;

	public YoutubeApiRestService(ProcessBatchConfig processBatchConfig) {
		this.processBatchConfig = processBatchConfig;
		this.webClient = WebClient.builder().baseUrl(getRestBase()).build();
	}

	public ResponseDTO getVideoData() {

		ResponseDTO responseMono = this.webClient
				.get()
				.uri(
						uriBuilder -> uriBuilder
								.queryParam("part", processBatchConfig.getPart())
								.queryParam("q", processBatchConfig.getSearchTerm())
								.queryParam("key", processBatchConfig.getApiKey())
								.queryParam("maxResults", processBatchConfig.getMaxResults())
								.queryParam("order", processBatchConfig.getOrder())
								.queryParam("pageToken", processBatchConfig.getNextPage())
								.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ResponseDTO.class)
				.block();
		log.info("Json: " + responseMono.toString());

		return responseMono;
	}

	// 'https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=Klimawandel&key=AIzaSyB99Lqjk9z18wSFKsktjcD3b-8fkD2S8jo'
	private String getRestBase() {
		return "https://youtube.googleapis.com/youtube/v3/search";
	}
}
