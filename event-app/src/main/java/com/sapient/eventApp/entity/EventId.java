package com.sapient.eventApp.entity;

import java.time.LocalDate;

public class EventId {
	private int id;
	private LocalDate eventDate;
	public EventId(int id, LocalDate eventDate) {
		this.id = id;
		this.eventDate = eventDate;
	}
	
	public EventId() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	
}
