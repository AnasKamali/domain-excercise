package com.sapient.eventApp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.entity.EventId;
import com.sapient.eventApp.exception.EventDoesNotExistsException;
import com.sapient.eventApp.repository.EventRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceDbImplTest {
	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventServiceDbImpl eventServiceDbImpl;

	private Optional<Event> eventOptional;
	private Event event;
	private EventId eventId;
	private int id;
	private LocalDate eventDate;

	@BeforeEach
	public void setup() {
		id = 1;
		eventId = new EventId(id, LocalDate.now());
		event = new Event(id, LocalDate.now(), LocalTime.now(),
				LocalTime.now().plus(Long.valueOf(8l), ChronoUnit.HOURS));
		eventOptional = Optional.of(event);
		eventDate = LocalDate.now();

	}

	@Test
	void createEventOnSwipeInSuccess() {
		when(eventRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		String createEvent = eventServiceDbImpl.createEventOnSwipeIn(1);
		assertEquals("Event Created", createEvent);
	}

	@Test
	void createEventOnSwipeInFailure() {
		when(eventRepository.findById(ArgumentMatchers.any())).thenReturn(eventOptional);
		String createEvent = eventServiceDbImpl.createEventOnSwipeIn(1);
		assertEquals("Event Already Exists", createEvent);
	}

	@Test
	void updateEventFailure() throws EventDoesNotExistsException {
		when(eventRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		assertThrows(EventDoesNotExistsException.class,()-> eventServiceDbImpl.updateEventOnSwipeOut( event)) ;
	}

	@Test
	void updateEventSuccess() throws EventDoesNotExistsException {
		when(eventRepository.findById(ArgumentMatchers.any())).thenReturn(eventOptional);
		when(eventRepository.save(ArgumentMatchers.any())).thenReturn(event);
		
		assertDoesNotThrow(()->eventServiceDbImpl.updateEventOnSwipeOut( event));
	}

	@Test
	void calculateAttandanceForDate() {
		List<Event> events = getEvents();
		when(eventRepository.findByEventDate(ArgumentMatchers.any())).thenReturn(events);
		when(eventRepository.saveAll(ArgumentMatchers.any())).thenReturn(events);
		List<Event> calculateAttandanceForDate = eventServiceDbImpl.calculateAttandanceForDate(eventDate);
		assertEquals(events.size(), calculateAttandanceForDate.size());
	}

	List<Event> getEvents() {
		Event present = new Event(id, LocalDate.now(), LocalTime.now(),
				LocalTime.now().plus(Long.valueOf(8l), ChronoUnit.HOURS));
		Event absent = new Event(id, LocalDate.now(), LocalTime.now(),
				LocalTime.now().plus(Long.valueOf(8l), ChronoUnit.HOURS));
		Event halfday = new Event(id, LocalDate.now(), LocalTime.now(),
				LocalTime.now().plus(Long.valueOf(8l), ChronoUnit.HOURS));
		return Arrays.asList(present, absent, halfday);
	}
}
