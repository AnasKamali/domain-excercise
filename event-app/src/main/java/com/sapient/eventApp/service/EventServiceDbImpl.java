package com.sapient.eventApp.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.entity.EventId;
import com.sapient.eventApp.exception.EventDoesNotExistsException;
import com.sapient.eventApp.producer.EventProducer;
import com.sapient.eventApp.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventServiceDbImpl implements EventService {
	private final Logger logger = LoggerFactory.getLogger(EventServiceDbImpl.class);
	private EventRepository eventRepository;

	public EventServiceDbImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	@Transactional
	public String createEvent(int id) {
		Optional<Event> findById = eventRepository.findById(new EventId(id, LocalDate.now()));
		if (findById.isEmpty()) {
			eventRepository.save(new Event(id, LocalDate.now(), LocalTime.now(), null));
			return "Event Created";
		} else {
			return "Event Already Exists";
		}
	}

	@Override
	@Transactional
	public String updateEvent(Event event) throws EventDoesNotExistsException {
		Optional<Event> findById = eventRepository.findById(new EventId(event.getId(), event.getDate()));
		if (findById.isEmpty()) {
			throw new EventDoesNotExistsException("event does not exists:" + event);
		} else {
			Event existingEvent = findById.get();
			existingEvent.setSwipeOutTime(event.getSwipeOutTime());
			eventRepository.save(existingEvent);
			logger.info("Updated Event {}", existingEvent);
			return "Event Updated:" + existingEvent;
		}
	}

	@Override
	@Transactional
	public List<Event> calculateAttandanceForDate(LocalDate eventDate) {
		Set<Event> events = eventRepository.findByEventDate(eventDate);
		for (Event e : events) {
			int duration = (int) Duration.between(e.getSwipeInTime(), e.getSwipeOutTime()).toHours();
			e.setTotalHourAttanded(duration);
			if (duration < 4) {
				e.setStatus("Absent");
			} else if (duration < 8) {
				e.setStatus("Half Day");
			} else {
				e.setStatus("Present");
			}
		}
		return  (List<Event>) eventRepository.saveAll(events);
	}

	@Override
	public Set<Event> produceEvent(LocalDate eventDate) {
		Set<Event> events = eventRepository.findByEventDate(eventDate);
		EventProducer.produceEvents(events);
		return events;
	}

}
