package com.sapient.attandanceApp.consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.attandanceApp.entity.Event;

@ExtendWith(MockitoExtension.class)
class KafkaObjctConsumerTest {
	@Mock
	KafkaConsumer<String, Event> kafkaConsumer;
	@InjectMocks
	KafkaObjctConsumer kafkaObjctConsumer;

	@Test
	void consumeAttendance() {
		when(kafkaConsumer.poll(ArgumentMatchers.any())).thenReturn(new ConsumerRecords<>(Collections.emptyMap()));
		List<Event> consumeAttendance = kafkaObjctConsumer.consumeAttendance();
		assertNotNull(consumeAttendance);
	}

}
