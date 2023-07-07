package com.sapient.eventApp.producer;

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

import com.sapient.eventApp.entity.Event;
import com.sapient.eventApp.util.ObjectDeserializer;

@Component
public class KafkaObjctConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaObjctConsumer.class);
	private static KafkaConsumer<String, Event> kafkaConsumer;

	public static void main(String args[]) {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.deserializer", StringDeserializer.class.getName());
		p.setProperty("value.deserializer", ObjectDeserializer.class.getName());
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "attendance_app_group");
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		kafkaConsumer = new KafkaConsumer<>(p);
		kafkaConsumer.subscribe(Arrays.asList("attendance_status"));
		ConsumerRecords<String, Event> records = null;
		
		List<Event> events = new ArrayList<>();
		try {
			records = kafkaConsumer.poll(Duration.ofMillis(1000));
			for (ConsumerRecord<String, Event> consumerRecord : records) {
				System.err.println(consumerRecord.value());
				Event event = consumerRecord.value();
				events.add(event);
			}
		} catch (WakeupException e) {
			logger.info(e.getLocalizedMessage());
		}
	}

}
