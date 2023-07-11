package com.sapient.attandanceApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.attandanceApp.consumer.KafkaObjctConsumer;
import com.sapient.attandanceApp.entity.Attendance;
import com.sapient.attandanceApp.entity.Event;
import com.sapient.attandanceApp.repository.AttendanceRepository;
import com.sapient.attandanceApp.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {
	@Mock
	private AttendanceRepository attendanceRepository;
	@Mock
	private EmployeeRepository employeeRepository;
	@Mock
	private KafkaObjctConsumer kafkaObjctConsumer;

	@InjectMocks
	private AttendanceServiceImpl attendanceServiceImpl;

	@Test
	void getAttandanceByDate( ) {
		when(attendanceRepository.findByIdAttendanceDate(ArgumentMatchers.any())).thenReturn(new ArrayList<Attendance>());
		List<Attendance> attandanceByDate = attendanceServiceImpl.getAttandanceByDate(LocalDate.now());
		assertEquals(ArrayList.class, attandanceByDate.getClass());
	}

	@Test
	void getAttandanceByEmployeeId( ) {
		when(attendanceRepository.findByIdEmployeeId(ArgumentMatchers.anyInt())).thenReturn(new ArrayList<Attendance>());
		List<Attendance> attandanceByDate = attendanceServiceImpl.getAttandanceByEmployeeId(1);
		assertEquals(ArrayList.class, attandanceByDate.getClass());
	}

	@Test
	void consumeAttendanceEvents() {
		when(kafkaObjctConsumer.consumeAttendance()).thenReturn(Arrays.asList(new Event()));
		when(employeeRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);
		when(attendanceRepository.save(ArgumentMatchers.any())).thenReturn(new Attendance());

		attendanceServiceImpl.consumeAttendanceEvents();
		verify(employeeRepository,times(1)).existsById(ArgumentMatchers.anyInt());
		verify(attendanceRepository,times(1)).save(ArgumentMatchers.any());
	}

}
