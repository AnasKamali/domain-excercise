package com.sapient.eventApp.main;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class TestKafka {
	public static void main(String[] args) {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.serializer", StringSerializer.class.getName());
		p.setProperty("value.serializer", StringSerializer.class.getName());
		KafkaProducer<String, String> kp = new KafkaProducer<>(p);
		ProducerRecord<String, String> r = new ProducerRecord<>("first_topic", "hello2");
		kp.send(r, (metadata, exception) -> {
			if (exception == null) {
				System.err.println(metadata.topic());
			} else {
				System.err.println(exception.getLocalizedMessage());
			}

		});
		kp.flush();
		kp.close();

	}

}
