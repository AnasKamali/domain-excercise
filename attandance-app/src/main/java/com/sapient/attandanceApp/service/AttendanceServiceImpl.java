package com.sapient.attandanceApp.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.sapient.attandanceApp.consumer.KafkaObjctConsumer;
import com.sapient.attandanceApp.entity.Attendance;
import com.sapient.attandanceApp.entity.Event;
import com.sapient.attandanceApp.entity.Attendance.AttandanceBuilder;
import com.sapient.attandanceApp.repository.AttendanceRepository;
import com.sapient.attandanceApp.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	EmployeeRepository employeeRepository;
	AttendanceRepository attendanceRepository;
	KafkaObjctConsumer kafkaObjctConsumer;

	public AttendanceServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository,
			KafkaObjctConsumer kafkaObjctConsumer) {
		this.employeeRepository = employeeRepository;
		this.attendanceRepository = attendanceRepository;
		this.kafkaObjctConsumer = kafkaObjctConsumer;
	}

	@Override
	public List<Attendance> getAttandanceByDate(LocalDate date) {
		return attendanceRepository.findByIdAttendanceDate(date);
	}

	@Override
	public List<Attendance> getAttandanceByEmployeeId(int employeeId) {
		return attendanceRepository.findByIdEmployeeId(employeeId);
	}

	@Override
	@Transactional
	public void createAttendance() {
		List<Event> events = kafkaObjctConsumer.consumeAttendance();
		for (Event event : events) {
			if (employeeRepository.existsById(event.getId())) {
				Attendance attendance = AttandanceBuilder.build(event);
				attendanceRepository.save(attendance);
			}
		}
	}

}
