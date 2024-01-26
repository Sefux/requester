package com.gpe_learn.video_request.delegate;

import com.gpe_learn.video_request.config.ProcessBatchConfig;
import com.gpe_learn.video_request.model.ResponseDTO;
import com.gpe_learn.video_request.model.Video;
import com.gpe_learn.video_request.repository.VideoRepository;
import com.gpe_learn.video_request.service.YoutubeApiRestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateAPICallDelegate implements JavaDelegate {

    private final ProcessBatchConfig processBatchConfig;

    @Autowired
    private VideoRepository repository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer createdApiCount = (Integer) delegateExecution.getVariable("createdApiCount");
        Integer batchNumber = createdApiCount++;
        log.info("Created APIcall count: {} ", createdApiCount);
        log.info("Batch number: {} ", batchNumber);
        log.info("Next page number: {} ", processBatchConfig.getNextPage());
        log.info("Max results per page: {} ", processBatchConfig.getMaxResults());

        if (batchNumber < processBatchConfig.getMaxApiCalls()) {
            createdApiCall(batchNumber, delegateExecution);
            delegateExecution.setVariable("allCreated", false);
        } else {
            delegateExecution.setVariable("allCreated", true);
        }
    }

    private void createdApiCall(Integer index, DelegateExecution execution) {
        ResponseDTO response = new YoutubeApiRestService(processBatchConfig).getVideoData();
        response.getItems().forEach(videoDto -> {
            Video existingVideo = repository.findByLink(videoDto.getLink());
            if (existingVideo == null) {
                // Create a new video
                repository.save(new Video(videoDto.getTitle(), videoDto.getLink()));
                processBatchConfig.setSavedVideos(processBatchConfig.getSavedVideos() + 1);
                log.info("[SAVED] Video saved: " + videoDto.getTitle());
            } else {
                // A video already exists, no action needed
                log.warn("[SKIPPED] Video already in database: " + videoDto.getTitle());
            }
        });
        processBatchConfig.setNextPage(response.getNextPageToken());
        execution.setVariable("nextPageToken", processBatchConfig.getNextPage());
        execution.setVariable("savedVideos", processBatchConfig.getSavedVideos());
        execution.setVariable("createdApiCount", index);
    }
}