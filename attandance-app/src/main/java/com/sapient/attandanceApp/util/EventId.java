package com.sapient.attandanceApp.util;

import java.time.LocalDate;
import java.util.Objects;


public class EventId {
	private int employeeId;
	private LocalDate eventDate;

	public EventId(int employeeId, LocalDate eventDate) {
		this.employeeId = employeeId;
		this.eventDate = eventDate;
	}

	public EventId() {
	}

	public int getId() {
		return employeeId;
	}

	public void setId(int id) {
		this.employeeId = id;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, eventDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventId other = (EventId) obj;
		return employeeId == other.employeeId && Objects.equals(eventDate, other.eventDate);
	}

}
