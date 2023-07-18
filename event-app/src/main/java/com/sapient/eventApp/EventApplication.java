package com.sapient.eventApp;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sapient.eventApp.exception.handler.RestTemplateErrorHandler;
import com.sapient.eventApp.model.Event;
import com.sapient.eventApp.util.ObjectSerializer;

@SpringBootApplication
//@EnableDiscoveryClient
public class EventApplication {
	@Value("${kafka.server:127.0.0.1:29092}")
	String kafkaServers;

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.errorHandler(new RestTemplateErrorHandler()).build();
	}

	@Bean
	public KafkaProducer<String, Event> getKafkaProducer() {
		Properties p = new Properties();
		p.setProperty("bootstrap.servers", kafkaServers);
		p.setProperty("key.serializer", StringSerializer.class.getName());
		p.setProperty("value.serializer", ObjectSerializer.class.getName());
		return new KafkaProducer<>(p);
	}

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

}
