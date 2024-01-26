package com.gpe_learn.video_request.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.gpe_learn.video_request.config.ProcessBatchConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class GenerateBatchDelegate implements JavaDelegate {

    private final ProcessBatchConfig processBatchConfig;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String searchterm = (String) execution.getVariable("searchTerm");
        String maxResults = (String) execution.getVariable("maxResults");
        String maxApiCalls = (String) execution.getVariable("maxApiCalls");

        processBatchConfig.setSearchTerm(searchterm);
        processBatchConfig.setMaxResults(maxResults);
        processBatchConfig.setMaxApiCalls(Integer.parseInt(maxApiCalls));
        
        execution.setVariable("createdApiCount", 0);
        execution.setVariable("totalResults", 0);
        
        log.info("### START API CONSUMER ###");
        log.info("Searchterm: "  + processBatchConfig.getSearchTerm() );
        log.info("Max results: "  + processBatchConfig.getMaxResults() );
    }
}
