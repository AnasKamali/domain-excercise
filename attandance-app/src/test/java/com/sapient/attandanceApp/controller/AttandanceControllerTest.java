package com.sapient.attandanceApp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sapient.attandanceApp.entity.Attendance;
import com.sapient.attandanceApp.service.AttendanceService;

@WebMvcTest(AttandanceController.class)
class AttandanceControllerTest {

	@MockBean
	private AttendanceService attendanceService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAttandanceByDate() throws Exception {
		when(attendanceService.getAttandanceByDate(ArgumentMatchers.any())).thenReturn(Arrays.asList(new Attendance()));
        mockMvc.perform(get("/attendance/2023-01-12")).andExpect(status().isOk());
	}

	@Test
	void getAttandanceByEmployeeId() throws Exception {
		when(attendanceService.getAttandanceByEmployeeId(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(new Attendance()));
        mockMvc.perform(get("/employee/1/attandance")).andExpect(status().isOk());
	}

	@Test
	void consumeKafkaEvents() throws Exception {
		when(attendanceService.getAttandanceByEmployeeId(ArgumentMatchers.anyInt())).thenReturn(Arrays.asList(new Attendance()));
		mockMvc.perform(get("/consumeKafkaEvents")).andExpect(status().isOk());
	}

}
