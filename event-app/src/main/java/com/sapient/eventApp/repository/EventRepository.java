package com.sapient.eventApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.entity.EventId;
@Repository
public interface EventRepository extends CrudRepository<Event, EventId> {
	public List<Event> findByEventDate(LocalDate eventDate);

}
