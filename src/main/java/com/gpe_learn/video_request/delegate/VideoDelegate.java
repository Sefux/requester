package com.gpe_learn.video_request.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpe_learn.video_request.model.Video;
import com.gpe_learn.video_request.repository.VideoRepository;

import org.camunda.spin.plugin.variable.value.JsonValue;
import org.json.JSONArray;
import org.json.JSONObject;

@Component
public class VideoDelegate implements JavaDelegate {

    @Autowired
    private VideoRepository repository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        JsonValue content = (JsonValue) execution.getVariableTyped("items");
        JSONArray jsonArr = new JSONArray(content.getValueSerialized());

        System.out.println("\n\n\n######\n\n\n");

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            JSONObject jsonId = jsonObj.getJSONObject("id");
            JSONObject jsonSnippet = jsonObj.getJSONObject("snippet");

            String videoId = jsonId.get("videoId").toString();
            String videoTitle = jsonSnippet.get("title").toString();

            Video existingVideo = repository.findByLink(videoId);

            if (existingVideo == null) {
                // Create a new video
                repository.save(new Video(videoTitle, videoId));
                System.out.println("[SAVED] Video saved: " + videoTitle);
            } else {
                // A video already exists, no action needed
                System.out.println("[SKIPPED] Video already in database: " + videoTitle);
            }

        }
        System.out.println("\n\n\n######\n\n\n");
    }
}
