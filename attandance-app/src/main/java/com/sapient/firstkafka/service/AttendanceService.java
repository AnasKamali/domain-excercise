package com.sapient.firstkafka.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.firstkafka.entity.Attendance;

public interface AttendanceService {

	public List<Attendance> getAttandanceByDate(@PathVariable("date") LocalDate date);

	public List<Attendance> getAttandanceByEmployeeId(int id);

	public void createAttendance();

}