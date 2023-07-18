package com.sapient.attandanceApp;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.sapient.attandanceApp.util.Event;
import com.sapient.attandanceApp.util.ObjectDeserializer;

@SpringBootApplication
@EnableDiscoveryClient
public class AttandanceApplication {

	@Value("${kafka.server:127.0.0.1:29092}")
	String kafkaServer;
	@Value("${kafka.topic:attendance_status}")
	String kafkaTopic;
	@Value("${kafka.consumer.groupId:attendance_application}")
	String kafkaConsumrGroupId;
	@Value("${kafka.consumer.cosumeOffset:earliest}")
	String kafkaConsumrOffset;

	public static void main(String[] args) {
		SpringApplication.run(AttandanceApplication.class, args);
	}

	@Bean
	public KafkaConsumer<String, Event> getKafkaConsumer() {
		Properties p = new Properties();

		p.setProperty("bootstrap.servers", kafkaServer);
		p.setProperty("key.deserializer", StringDeserializer.class.getName());
		p.setProperty("value.deserializer", ObjectDeserializer.class.getName());
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumrGroupId);
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumrOffset);
		KafkaConsumer<String, Event> kafkaConsumer = new KafkaConsumer<>(p);
		kafkaConsumer.subscribe(Arrays.asList(kafkaTopic));
		return kafkaConsumer;
	}

}
