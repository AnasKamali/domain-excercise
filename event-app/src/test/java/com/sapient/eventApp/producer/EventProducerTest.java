//package com.sapient.eventApp.producer;
//
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Future;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.sapient.eventApp.entity.Event;
//
//@ExtendWith(MockitoExtension.class)
//class EventProducerTest {
//	@Mock
//	KafkaProducer<String, Event> kafkaProducer;
//	@InjectMocks
//	EventProducer eventProducer;
//
//	@BeforeEach
//	void setup() {
//		ReflectionTestUtils.setField(eventProducer, "kafkaTopic", "topic");
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Test
//	void produceEvents() {
//		when(kafkaProducer.send(ArgumentMatchers.any(), ArgumentMatchers.any()))
//				.thenReturn((Future) CompletableFuture.completedFuture(new Object()));
//		doNothing().when(kafkaProducer).flush();
//		eventProducer.produceEvents(List.of(new Event()));
//		verify(kafkaProducer, times(1)).send(ArgumentMatchers.any(), ArgumentMatchers.any());
//		verify(kafkaProducer, times(1)).flush();
//	}
//
//}
