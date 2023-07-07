package com.sapient.firstkafka.controller;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.firstkafka.entity.Attendance;
import com.sapient.firstkafka.service.AttendanceService;

@RestController
public class AttandanceController {
	private static Logger logger = LoggerFactory.getLogger(AttandanceController.class);
	AttendanceService attendanceService;

	public AttandanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@GetMapping("/attendance/{date}")
	public List<Attendance> getAttandanceByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		logger.info("getting attandance for date {}", date);
		return attendanceService.getAttandanceByDate(date);

	}

	@GetMapping("/employee/{id}/attandance")
	public List<Attendance> getAttandanceByEmployeeId(@PathVariable("id") int id) {
		logger.info("getting attandance for employee with id {}", id);
		return attendanceService.getAttandanceByEmployeeId(id);

	}

	@GetMapping("/createAttendance")
	public void createAttendance() {
		logger.info("createAttendance ");
		attendanceService.createAttendance();

	}

}
