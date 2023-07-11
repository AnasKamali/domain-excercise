package com.sapient.attandanceApp.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sapient.attandanceApp.entity.Event;

@Component
public class KafkaObjctConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaObjctConsumer.class);
	private KafkaConsumer<String, Event> kafkaConsumer;

	public KafkaObjctConsumer(KafkaConsumer<String, Event> kafkaConsumer) {
		this.kafkaConsumer = kafkaConsumer;

	}

	public List<Event> consumeAttendance() {

		ConsumerRecords<String, Event> records = null;
		List<Event> events = new ArrayList<>();
		int count = 0;
		try {
			do {
				logger.error("count:{}", ++count);
				records = kafkaConsumer.poll(Duration.ofMinutes(2));
				for (ConsumerRecord<String, Event> consumerRecord : records) {
					Event event = consumerRecord.value();
					events.add(event);
				}
			} while (!records.isEmpty());
		} catch (WakeupException e) {
			logger.info(e.getLocalizedMessage());
		}
		return events;
	}

}
