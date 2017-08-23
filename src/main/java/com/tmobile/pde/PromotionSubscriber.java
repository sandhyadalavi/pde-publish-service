package com.tmobile.pde;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class PromotionSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message) {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        Object obj = jackson2JsonMessageConverter.fromMessage(message);
        System.out.println("Received <" + obj + ">");
    }
}
