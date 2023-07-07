package com.sapient.firstkafka.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sapient.firstkafka.entity.Event;
import com.sapient.firstkafka.exception.DeserializationException;

public class ObjectSerializer implements Serializer<Event> {

	@Override
	public byte[] serialize(String topic, Event data) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			throw new DeserializationException(e.getLocalizedMessage());
		}
	}

}
