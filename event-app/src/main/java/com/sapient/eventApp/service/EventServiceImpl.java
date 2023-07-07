package com.sapient.eventApp.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.producer.EventProducer;

public class EventServiceImpl implements EventService  {
	private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	private static Set<Event> events = new HashSet<>();
	
	


	@Override
	public String createEvent(int id) {
		Event event = new Event(id, LocalDate.now(), LocalTime.now(), null);
		if (events.contains(event)) {
			logger.info("Adding Event {}", event);
			return "Event Already Exist";
		} else {
			events.add(event);
			return "Event Created";
		}
	}

	@Override
	public String updateEvent(Event event) {
		if (events.contains(event)) {
			for (Event existingEvent : events) {
				if (event.equals(existingEvent)) {
					existingEvent.setSwipeOutTime(event.getSwipeOutTime());
					logger.info("Updated Event {}", existingEvent);

				}
			}
			return "Event Already Exist";
		} else {
			return "Event Does Not Exists";
		}
	}

	@Override
	public Set<Event> calculateAttandanceForDate(LocalDate localDate) {
		for (Event e : events) {
			int duration = Duration.between(e.getSwipeInTime(), e.getSwipeOutTime()).toMinutesPart();
			e.setTotalHourAttanded(duration);
			if (duration < 4) {
				e.setStatus("Absent");
			} else if (duration < 8) {
				e.setStatus("Half Day");
			} else {
				e.setStatus("Present");
			}
		}
		return events;
	}
	@Override
	public Set<Event> produceEvent(LocalDate localDate) {
		for (Event e : events) {
			int duration = Duration.between(e.getSwipeInTime(), e.getSwipeOutTime()).toMinutesPart();
			e.setTotalHourAttanded(duration);
			if (duration < 4) {
				e.setStatus("Absent");
			} else if (duration < 8) {
				e.setStatus("Half Day");
			} else {
				e.setStatus("Present");
			}
		}
		EventProducer.produceEvents(events);
		return events;
	}

}
