package com.tmobile.pde;

import com.tmobile.pde.model.Promotion;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		for(int i = 0; i< 10; i++) {
			Promotion promotion = new Promotion();
			promotion.setPromoId(i);
			rabbitTemplate.convertAndSend("mpm-data", "mpmKey", promotion);
		}
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("mpm-queue");
		container.setMessageListener(new PromotionSubscriber());
		container.setMessageConverter(new Jackson2JsonMessageConverter());
		return container;
	}

}
