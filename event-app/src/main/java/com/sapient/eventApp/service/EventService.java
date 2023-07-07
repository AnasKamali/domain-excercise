package com.sapient.eventApp.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.exception.EventDoesNotExistsException;

public interface EventService {

	String createEvent(int id);

	String updateEvent(Event event) throws EventDoesNotExistsException;

	Collection<Event> calculateAttandanceForDate(LocalDate localDate);

	Set<Event> produceEvent(LocalDate localDate);

}