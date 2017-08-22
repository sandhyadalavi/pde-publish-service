package com.tmobile.pde;

import com.tmobile.pde.model.Promotion;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublishServiceApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PublishServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		rabbitTemplate.convertAndSend("mpm-data", "mpmKey", new Promotion());
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
													MessageListenerAdapter listenerAdapter,
													MessageConverter messageConverter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("mpm-queue");
		container.setMessageListener(listenerAdapter);
		container.setMessageConverter(messageConverter);
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(PromotionSubscriber promotionSubscriber) {
		return new MessageListenerAdapter(promotionSubscriber);
	}

}
