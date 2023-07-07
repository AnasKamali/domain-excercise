package com.sapient.firstkafka.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Event {
	private int id;
	private LocalDate eventDate;
	private LocalTime swipeInTime;
	private LocalTime swipeOutTime;

	private String status;
	private int totalHourAttanded;


	public Event(int id, LocalDate eventDate, LocalTime swipeInTime, LocalTime swipeOutTime) {
		super();
		this.id = id;
		this.eventDate = eventDate;
		this.swipeInTime = swipeInTime;
		this.swipeOutTime = swipeOutTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return eventDate;
	}

	public void setDate(LocalDate eventDate) {
		this.eventDate = eventDate;
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

	public Event() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventDate, id);
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
		return Objects.equals(eventDate, other.eventDate) && id == other.id;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", date=" + eventDate + ", swipeInTime=" + swipeInTime + ", swipeOutTime="
				+ swipeOutTime + ", status=" + status + ", totalHourAttanded=" + totalHourAttanded + "]";
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

}
