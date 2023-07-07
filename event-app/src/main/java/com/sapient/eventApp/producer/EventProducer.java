package com.sapient.eventApp.producer;

import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.util.ObjectSerializer;

public class EventProducer {
	private static final Logger logger= LoggerFactory.getLogger(EventProducer.class);
	private EventProducer() {}

	public static void produceEvents(Set<Event> events) {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.serializer", StringSerializer.class.getName());
		p.setProperty("value.serializer", ObjectSerializer.class.getName());
		KafkaProducer<String, Event> kp = new KafkaProducer<>(p);
		for (Event event : events) {
			kp.send(new ProducerRecord<>("attendance_status", Integer.toString(event.getId()), event),
					(metadata, exception) -> {
						if (exception == null) {
							logger.info(metadata.topic());
						} else {
							logger.info(exception.getLocalizedMessage());
						}
					});
		}
		kp.flush();
		kp.close();

	}

}
