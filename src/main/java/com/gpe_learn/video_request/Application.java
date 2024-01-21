package com.gpe_learn.video_request;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;


// import com.gpe_learn.video_request.model.Video;
// import com.gpe_learn.video_request.repository.VideoRepository;

@SpringBootApplication
@EnableProcessApplication
public class Application {

  @Autowired
  private RuntimeService runtimeService;


  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @EventListener
  public void processPostDeploy(PostDeployEvent event) {
    runtimeService.startProcessInstanceByKey("video_request-process");
  }

  // 	@Bean
	// CommandLineRunner commandLineRunner(VideoRepository videoRepository) {
	// 	return args -> {
	// 		List<Video> videos = List.of(new Video( "Video1", "https://yt.com/1"), new Video("Titel 2", "https://yt.com/2"));
	// 		videoRepository.saveAll(videos);

	// 		videoRepository.findAll().stream().forEach(System.out::println);
	// 	};
	// }


}