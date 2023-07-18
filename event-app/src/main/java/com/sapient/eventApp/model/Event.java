package com.sapient.eventApp.model;

import java.time.LocalTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
	@Id
	private String id;
	@Indexed(unique = true)
	private EventId eventId;
	private LocalTime swipeInTime;
	private LocalTime swipeOutTime;

	private String status;
	private int totalHourAttanded;

	public Event() {
	}

	public Event(EventId eventId) {
		super();
		this.eventId = eventId;
	}

	public Event( EventId eventId, LocalTime swipeInTime, LocalTime swipeOutTime) {
		this.eventId = eventId;
		this.swipeInTime = swipeInTime;
		this.swipeOutTime = swipeOutTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EventId getEventId() {
		return eventId;
	}

	public void setEventId(EventId eventId) {
		this.eventId = eventId;
	}

	public LocalTime getSwipeInTime() {
		return swipeInTime;
	}

	public void setSwipeInTime(LocalTime swipeInTime) {
		this.swipeInTime = swipeInTime;
	}

	public LocalTime getSwipeOutTime() {
		return swipeOutTime;
	}

	public void setSwipeOutTime(LocalTime swipeOutTime) {
		this.swipeOutTime = swipeOutTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalHourAttanded() {
		return totalHourAttanded;
	}

	public void setTotalHourAttanded(int totalHourAttanded) {
		this.totalHourAttanded = totalHourAttanded;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventId=" + eventId + ", swipeInTime=" + swipeInTime + ", swipeOutTime="
				+ swipeOutTime + ", status=" + status + ", totalHourAttanded=" + totalHourAttanded + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(eventId, other.eventId) && Objects.equals(id, other.id);
	}

}
