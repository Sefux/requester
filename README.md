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

* Microservices in Java
https://camunda.com/resources/microservices/java/



* Rest-Service Task
  https://github.com/camunda/camunda-bpm-examples/tree/master/servicetask/rest-service


* Camunda BPMN Examples
https://github.com/camunda/camunda-bpm-examples

* Camunda BPMN Examples - REST Service Task
https://github.com/camunda/camunda-bpm-examples/tree/master/servicetask/rest-service


## Snippets

### Connector output

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