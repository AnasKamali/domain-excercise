package com.sapient.eventApp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/swipeIn/{id}")
	public ResponseEntity<String> swipeIn(@PathVariable("id") int id) {
		logger.info("swipe in with id {}", id);

		eventService.createEventOnSwipeIn(id);
		return ResponseEntity.ok("successfully swiped in with id:" + id);
	}

	@GetMapping("/swipeOut/{id}")
	public ResponseEntity<String> swipeOut(@PathVariable("id") int id) throws EventDoesNotExistsException {
		logger.info("swipe out with id {}", id);
		Event event = new Event(id, LocalDate.now(), null, LocalTime.now());
		eventService.updateEventOnSwipeOut(event);
		return ResponseEntity.ok("successfully swiped out with id:" + id);
	}

	@GetMapping("/calculateAttendance")
	public Collection<Event> calculateAttendance() {
		logger.info("calculating attandance for date {}", LocalDate.now());
		return eventService.calculateAttandanceForDate(LocalDate.now());

	}

	@GetMapping("/publishEvents")
	public List<Event> publishEvents() {
		logger.info("calculating attandance for date {}", LocalDate.now());
		return eventService.publishEvents(LocalDate.now());

	}

}
