package com.sapient.attandanceApp.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sapient.attandanceApp.entity.Event;
import com.sapient.attandanceApp.util.ObjectDeserializer;

@Component
public class KafkaObjctConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaObjctConsumer.class);
	private static KafkaConsumer<String, Event> kafkaConsumer;

	public static KafkaConsumer<String, Event> getKafkaConsumer() {
		if (kafkaConsumer == null) {
			Properties p = new Properties();

			p.setProperty("bootstrap.servers", "127.0.0.1:29092");
			p.setProperty("key.deserializer", StringDeserializer.class.getName());
			p.setProperty("value.deserializer", ObjectDeserializer.class.getName());
			p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "attendance_app_group_2");
			p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
			kafkaConsumer = new KafkaConsumer<>(p);
			kafkaConsumer.subscribe(Arrays.asList("attendance_status"));
		}
		return kafkaConsumer;
	}

	public List<Event> consumeAttendance() {
		KafkaConsumer<String, Event> consumer = getKafkaConsumer();

		ConsumerRecords<String, Event> records = null;
		List<Event> events = new ArrayList<>();
		int count = 0;
		try {
			do {
				logger.error("count:{}", ++count);
				records = consumer.poll(Duration.ofMinutes(2));
				for (ConsumerRecord<String, Event> consumerRecord : records) {
					Event event = consumerRecord.value();
					events.add(event);
				}
			} while (records.isEmpty());
		} catch (WakeupException e) {
			logger.info(e.getLocalizedMessage());
		}
		return events;
	}

}
