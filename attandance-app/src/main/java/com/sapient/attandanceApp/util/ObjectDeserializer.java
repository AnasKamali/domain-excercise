package com.sapient.attandanceApp.util;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sapient.attandanceApp.exception.DeserializationException;

public class ObjectDeserializer implements Deserializer<Event> {

	@Override
	public Event deserialize(String topic, byte[] data) {
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.registerModule(new JavaTimeModule());
		try {
			return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Event.class);
		} catch ( JsonProcessingException e) {
			throw new DeserializationException(e.getLocalizedMessage());
		}
	}

}
