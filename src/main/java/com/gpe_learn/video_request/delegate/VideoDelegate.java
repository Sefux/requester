package com.gpe_learn.video_request.delegate;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.camunda.spin.plugin.variable.value.JsonValue;

@Component
public class VideoDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        JsonValue content = (JsonValue) execution.getVariableTyped("items", false);
        
        System.out.println("NOW WE WOULD PERSIST: '" + content.toString() + "'");
        System.out.println("\n\n\n######\n\n\n");
    }  
}
