package com.sapient.attandanceApp;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.sapient.attandanceApp.entity.Event;
import com.sapient.attandanceApp.util.ObjectDeserializer;

@SpringBootApplication
@EnableDiscoveryClient
public class AttandanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttandanceApplication.class, args);
	}

	@Bean
	public static KafkaConsumer<String, Event> getKafkaConsumer() {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", "127.0.0.1:29092");
		p.setProperty("key.deserializer", StringDeserializer.class.getName());
		p.setProperty("value.deserializer", ObjectDeserializer.class.getName());
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "attendance_app_group_2");
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		KafkaConsumer<String, Event> kafkaConsumer = new KafkaConsumer<>(p);
		kafkaConsumer.subscribe(Arrays.asList("attendance_status"));
		return kafkaConsumer;
	}

}
