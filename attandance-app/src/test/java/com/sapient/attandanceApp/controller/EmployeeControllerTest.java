package com.sapient.attandanceApp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.attandanceApp.entity.Employee;
import com.sapient.attandanceApp.service.EmployeeServiceImplementation;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@MockBean
	private EmployeeServiceImplementation employeeServiceImplementation;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllEmployee() throws Exception {
		when(employeeServiceImplementation.getAllEmployee()).thenReturn(Arrays.asList(new Employee()));
        mockMvc.perform(get("/employee")).andExpect(status().isOk());
	}

	@Test
	void getEmployee() throws Exception {
		when(employeeServiceImplementation.getEmployee(ArgumentMatchers.anyInt())).thenReturn(new Employee());
		mockMvc.perform(get("/employee/1")).andExpect(status().isOk());
	}

	@Test
	void createEmployee() throws Exception {
		when(employeeServiceImplementation.createEmployee(ArgumentMatchers.any())).thenReturn(new Employee());
		mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper()
						.writeValueAsBytes(new Employee())))
		.andExpect(status().isCreated());
	}

	@Test
	void deleteEmployee() throws Exception {
		doNothing().when(employeeServiceImplementation).deleteEmployee(ArgumentMatchers.anyInt());
		mockMvc.perform(delete("/employee/1")).andExpect(status().isOk());
	}

}
