package com.sapient.eventApp.mongo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sapient.eventApp.model.Event;
import com.sapient.eventApp.model.EventId;

public interface EventRepository extends MongoRepository<Event, String> {
	@Query("{'eventId.eventDate': ?0}")
	public List<Event> findByEventDate(LocalDate eventDate);
	
	public Optional<Event> findByEventId(EventId eventId);

}
