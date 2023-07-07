package com.sapient.eventApp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.exception.EventDoesNotExistsException;
import com.sapient.eventApp.service.EventService;

@RestController
public class EventController {

	private static Logger logger = LoggerFactory.getLogger(EventController.class);
	private EventService eventService;
	private RestTemplate restTemplate;

	public EventController(EventService eventService, RestTemplate restTemplate) {
		this.eventService = eventService;
		this.restTemplate = restTemplate;
	}

	

	@GetMapping("/distributedTracing")
	public ResponseEntity<String> distributedTracingTest() {
		logger.info("distributedTracingTest");
		ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/employee", HttpMethod.GET, null,
				String.class);
		logger.info("exchange {}", exchange.getBody());
		return ResponseEntity.ok("distributedTracingTest");
	}

	@GetMapping("/swipeIn/{id}")
	public ResponseEntity<String> swipeIn(@PathVariable("id") int id) {
		logger.info("swipe in with id {}", id);

		eventService.createEvent(id);
		return ResponseEntity.ok("successfully swiped in");
	}

	@GetMapping("/swipeOut/{id}")
	public ResponseEntity<String> swipeOut(@PathVariable("id") int id) throws EventDoesNotExistsException {
		logger.info("swipe out with id {}", id);
		Event event = new Event(id, LocalDate.now(), null, LocalTime.now());
		eventService.updateEvent(event);
		return ResponseEntity.ok("successfully swiped in");
	}

	@GetMapping("/calculateAttendance")
	public Collection<Event> calculateAttendance() {
		logger.info("calculating attandance for date {}", LocalDate.now());
		return eventService.calculateAttandanceForDate(LocalDate.now());

	}

	@GetMapping("/produceKafkaEvent")
	public Set<Event> produceKafkaEvent() {
		logger.info("calculating attandance for date {}", LocalDate.now());
		return eventService.produceEvent(LocalDate.now());

	}

}
