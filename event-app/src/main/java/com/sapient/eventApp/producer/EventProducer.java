package com.sapient.eventApp.producer;

import java.util.List;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sapient.eventApp.model.Event;

@Component
public class EventProducer {
	private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);
	KafkaProducer<String, Event> kafkaProducer;
	@Value("${kafka.topic:attendance_status}")
	private String kafkaTopic;

	public EventProducer(KafkaProducer<String, Event> kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	public void produceEvents(List<Event> events) {
		if (!events.isEmpty()) {
			for (Event event : events) {
				kafkaProducer.send(
						new ProducerRecord<>(kafkaTopic, Integer.toString(event.getEventId().getId()), event),
						(metadata, exception) -> {
							if (exception == null) {
								logger.info(metadata.topic());
							} else {
								logger.info(exception.getLocalizedMessage());
							}
						});
			}
			kafkaProducer.flush();
		}

	}

}
