package com.sapient.eventApp.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.sapient.eventApp.exception.EventDoesNotExistsException;
import com.sapient.eventApp.model.Event;


public interface EventService {

	String createEventOnSwipeIn(int id);

	String updateEventOnSwipeOut(Event event) throws EventDoesNotExistsException;

	Collection<Event> calculateAttandanceForDate(LocalDate localDate);

	List<Event> publishEvents(LocalDate localDate);

}