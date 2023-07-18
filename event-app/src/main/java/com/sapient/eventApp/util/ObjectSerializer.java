package com.sapient.eventApp.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sapient.eventApp.exception.SerializationException;
import com.sapient.eventApp.model.Event;
public class ObjectSerializer implements Serializer<Event> {

	@Override
	public byte[] serialize(String topic, Event data) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getLocalizedMessage());
		}
	}

}
