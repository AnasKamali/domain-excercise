package com.sapient.eventApp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.sapient.eventApp.exception.EventDoesNotExistsException;
import com.sapient.eventApp.model.Event;
import com.sapient.eventApp.service.EventServiceDbImpl;

@WebMvcTest(EventController.class)
class EventControllerTest {

	@MockBean
	private EventServiceDbImpl eventServiceDbImpl;
	@MockBean
	private RestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void swipeIn() throws Exception {
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
             .thenReturn(ResponseEntity.ok(null));
        when(eventServiceDbImpl.createEventOnSwipeIn(ArgumentMatchers.anyInt())).thenReturn("Event Created");
        mockMvc.perform(get("/swipeIn/1")).andExpect(status().isOk());
        
	}

	@Test
	void swipeOutSuccess() throws Exception {
		when(eventServiceDbImpl.updateEventOnSwipeOut(ArgumentMatchers.any())).thenReturn("Event Created");
		mockMvc.perform(get("/swipeOut/1")).andExpect(status().isOk());
		
	}

	@Test
	void swipeOutFailure() throws Exception {
		when(eventServiceDbImpl.updateEventOnSwipeOut(ArgumentMatchers.any())).thenThrow(EventDoesNotExistsException.class);
		mockMvc.perform(get("/swipeOut/1")).andExpect(status().is2xxSuccessful());
		
	}

	@Test
	void calculateAttendance() throws Exception {
		when(eventServiceDbImpl.calculateAttandanceForDate(ArgumentMatchers.any())).thenReturn(new ArrayList<Event>());
		mockMvc.perform(get("/calculateAttendance")).andExpect(status().isOk());
	}

	@Test
	void publishEvents() throws Exception {
		when(eventServiceDbImpl.publishEvents(ArgumentMatchers.any())).thenReturn(new ArrayList<Event>());
		mockMvc.perform(get("/publishEvents")).andExpect(status().isOk());
	}

}
