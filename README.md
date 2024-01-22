# Case Study - Green Planet Energy

## Aufgabe

* Camunda als Workflow Engine nutzen
* Abfrage eines Webservice und Verarbeiten der Ergebnisse
* Webservice definieren um den Prozess wiederholbar auszuführen

### Schritte

* Webservice nach Schlagwort abfragen
* Ergebniss parsen => Link / etc speichern
* Liste ausgeben


## Resourcen 


### Tutorials


#### Camunda 7

* Getting started
https://docs.camunda.org/get-started

  * Spring Boot
  https://docs.camunda.org/get-started/spring-boot/project-setup/

  * BPMN 2.0 Ref
  https://docs.camunda.org/manual/7.6/reference/bpmn20/

  * Delegating code
  https://docs.camunda.org/manual/7.6/user-guide/process-engine/delegation-code/#java-delegate

  * Process engine: Scripting
  https://docs.camunda.org/manual/7.6/user-guide/process-engine/scripting/

  * Spin dataformats: reading JSON
  https://docs.camunda.org/manual/7.6/reference/spin/json/01-reading-json/

* Microservices in Java
https://camunda.com/resources/microservices/java/

* Decoding Microservice: Best Practice
https://4513465.fs1.hubspotusercontent-na1.net/hubfs/4513465/Decoding-Microservices-Best-Practices-Handbook-for-Developers-2022%20(1).pdf


* Rest-Service Task
  https://github.com/camunda/camunda-bpm-examples/tree/master/servicetask/rest-service


* Camunda BPMN Examples
https://github.com/camunda/camunda-bpm-examples

* Camunda BPMN Examples - REST Service Task
https://github.com/camunda/camunda-bpm-examples/tree/master/servicetask/rest-service


* Camunda HTTP Connector Example
https://github.com/rob2universe/camunda-http-connector-example


* Video: Camunda 7: Application with Spring Boot
https://www.youtube.com/playlist?list=PLJG25HlmvsOVssaiPmavxv3htN_dXS3BW

* Process with REST / SwaggerUI
https://camunda.com/blog/2021/10/start-and-step-through-a-process-with-rest-feat-swaggerui/


#### Java / Spring

* Building REST services with spring
https://spring.io/guides/tutorials/rest/

* Generating UUID as Primary Key
https://www.baeldung.com/java-hibernate-uuid-primary-key

* Accesing data with JPA
https://spring.io/guides/gs/accessing-data-jpa/

* New CRUD Repository Interface
https://www.baeldung.com/spring-data-3-crud-repository-interfaces
https://spring.io/blog/2022/02/22/announcing-listcrudrepository-friends-for-spring-data-3-0/



### Links

* Camunda 7 Initializr
https://start.camunda.com/

* Spring Initializr
https://start.spring.io/




## Snippets

### Connector output

* Convert SpinlIst to JavaList
https://forum.camunda.io/t/convert-spinlist-to-java-list-in-expression/20031

```java
import static org.camunda.spin.Spin.*;
import static org.camunda.spin.DataFormats.*;

import com.gpe_learn.video_request.model.Video;
import com.gpe_learn.video_request.repository.VideoRepository;


SpinJsonNode json = S(response, json());
SpinList items = json.prop("items").elements();

List<Video> videos = new ArrayList<>();
VideoRepository videoRepository = new VideoRepository();
			
items.forEach(videoJson -> {
  Video video = new Video(videoJson.prop("snippet").prop("title"), videoJson.prop("id").prop("videoId"))
  videos.add(video);
});

videoRepository.saveAll(videos);

// Customer customer = JSON("{\"customer\": \"Kermit\"}").mapTo(Customer.class);
```

```Java
import static org.camunda.spin.Spin.*;

SpinJsonNode json = JSON(response).prop("items");

List<com.gpe_learn.video_request.model.Video> videos = JSON(response).mapTo("java.util.ArrayList<com.gpe_learn.video_request.model.Video>");
```

* Data mapping and transformation (spin)
https://camunda.com/blog/2015/02/data-mapping-and-transformation-with/