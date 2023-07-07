package com.sapient.firstkafka.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Attendance {
	@EmbeddedId
	AttendanceId id;

	String status;
	int hourAttended;

	public AttendanceId getId() {
		return id;
	}

	public void setId(AttendanceId id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHourAttended() {
		return hourAttended;
	}

	public void setHourAttended(int hourAttended) {
		this.hourAttended = hourAttended;
	}

	public Attendance() {
	}

	public Attendance(AttendanceId id, String status, int hourAttended) {
		this.id = id;
		this.status = status;
		this.hourAttended = hourAttended;
	}

	@Embeddable
	public static class AttendanceId {
		LocalDate attendanceDate;
		int employeeId;

		public LocalDate getAttendanceDate() {
			return attendanceDate;
		}

		public void setAttendanceDate(LocalDate attendanceDate) {
			this.attendanceDate = attendanceDate;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}

		public AttendanceId() {
		}

		public AttendanceId(LocalDate attendanceDate, int employeeId) {
			this.attendanceDate = attendanceDate;
			this.employeeId = employeeId;
		}

	}

	public static class AttandanceBuilder {
		private AttandanceBuilder() {
		}

		public static Attendance build(Event event) {
			return new Attendance(new AttendanceId(event.getDate(), event.getId()), event.getStatus(),
					event.getTotalHourAttanded());
		}
	}

}
