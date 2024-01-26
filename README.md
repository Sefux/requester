# Case Study - GPE

## Aufgabe

* Camunda als Workflow Engine nutzen
* Abfrage eines Webservice und Verarbeitung der Ergebnisse
  * Videos über die Youtube API abrufen zur Thematik "Klimawandel"
* Webservice definieren um den Prozess wiederholbar auszuführen

### Vorgehen

* Spring Boot Projekt eingerichtet (requester)
* Camunda 7.20 als Dependency eingebunden
* BMPN Prozesse definiert

  #### 1. YouTube Video Requester (process.bmpn)
  Ein http-Connector wird per BMPN Notation eingebunden. Die API-Antworten werden per Java-Delegate in die Datenbank geschrieben
![Grafik process.bmpn](docs/images/bmpn_process_1.png?raw=true)

  #### 2. YouTube Video Requester Loop (process_yt_batch.bmpn)
  Über ein Eingabeformular und 2 Java-Delegate Klassen werden beliebig viele API-Calls ausgeführt. Dadurch kann der API Pagination Parameter "nextPage" genutzt werden. Datensätze werden in einer Datenbnak gespeichert.
![Grafik process_yt_batch.bmpn](docs/images/bmpn_process_2.png?raw=true)


### Weiterführende Überlegungen / Weitere Annahmen

* API Abfrage
  * Wann wurde die letzte Abfrage gestellt? Wiederholbare Abfragen?
  * Mit welchen Parametern? Welche Informationen sollen abgefragt werden?
    * Welche Felder (snippet/title) werden untersucht?
  * Welche Möglichkeiten bietet die API/Dataset?
    * Pagination
    * Captions/Untertitel
    * Kommentare
  * Webservice API abfragen / Parameter: Schlagwort, Anzahl Ergebnisse, Sortierung
    * Batch Verarbeitung
    * Scheduled Prozess
    * Indexer Job archivieren / speichern
* Ergebniss parsen & Daten speichern
  * Daten Definition: Welche Informationen sollen gespeichert werden?
  * Persistenz herstellen:
    * Relationale Datenbank
    * Solr / Lucene / ElasticSearch
  * Keinen Doubletten aufnehmen
* GUI für Anzeige der Ergebnisse / Liste ausgeben
* Tests schreiben


### Anforderungen

* Java 17
* Camunda 7.20
* Maven 3.9.6

### Installation

```
git clone git@github.com:Sefux/requester.git
```

```
mvn compile
```

```
mvn spring-boot:run
```


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

* H2 Database Interface
http://localhost:8080/h2-console/
