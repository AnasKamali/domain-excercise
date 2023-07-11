package com.sapient.eventApp.producer;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.util.ObjectSerializer;

public class EventProducer {
	private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

	private EventProducer() {
	}

	public static KafkaProducer<String, Event> getKafkaProducer() {
		Properties p = new Properties();
		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.serializer", StringSerializer.class.getName());
		p.setProperty("value.serializer", ObjectSerializer.class.getName());
		return new KafkaProducer<>(p);
	}

	public static void produceEvents(List<Event> events) {
		if (!events.isEmpty()) {
			KafkaProducer<String, Event> kafkaProducer = getKafkaProducer();
			Assert.notNull(kafkaProducer, "Not able to connect to kafka server");
			for (Event event : events) {
				kafkaProducer.send(new ProducerRecord<>("attendance_status", Integer.toString(event.getId()), event),
						(metadata, exception) -> {
							if (exception == null) {
								logger.info(metadata.topic());
							} else {
								logger.info(exception.getLocalizedMessage());
							}
						});
			}
			kafkaProducer.flush();
			kafkaProducer.close();
		}

	}

}
