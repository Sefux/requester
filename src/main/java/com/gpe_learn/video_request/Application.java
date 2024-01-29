package com.gpe_learn.video_request;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableProcessApplication
public class Application {

  static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String... args) {
    log.info("Before Starting application");
    SpringApplication.run(Application.class, args);
  }
}