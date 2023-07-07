package com.sapient.eventApp.main;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

public class TestKafkaConsumer {
	public static void main(String[] args) {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.deserializer", StringDeserializer.class.getName());
		p.setProperty("value.deserializer", StringDeserializer.class.getName());
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my_first_java_app");
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		KafkaConsumer<String, String> kc = new KafkaConsumer<String, String>(p);

		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {

				try {
					System.err.println("closing");
					kc.wakeup();
					mainThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		kc.subscribe(Arrays.asList("first_topic"));
		ConsumerRecords<String, String> records;
		try {
			while (true) {
				records = kc.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> record : records) {
					System.err.println(record.value());
				}
			}
		} catch (WakeupException e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
			kc.close();
		}
		// kc.close();

	}

}
