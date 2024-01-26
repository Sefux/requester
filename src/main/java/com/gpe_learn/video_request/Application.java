package com.gpe_learn.video_request;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@EnableProcessApplication
public class Application {

  // @Autowired
  // private RuntimeService runtimeService;

  static final Logger log = LoggerFactory.getLogger(Application.class);


  public static void main(String... args) {
    log.info("Before Starting application");
    SpringApplication.run(Application.class, args);
  }

  // @EventListener
  // public void processPostDeploy(PostDeployEvent event) {
  //   runtimeService.startProcessInstanceByKey("video_request-process");
  // }

}