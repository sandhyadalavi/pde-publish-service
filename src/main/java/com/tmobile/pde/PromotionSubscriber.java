package com.tmobile.pde;

import org.springframework.stereotype.Component;

@Component
public class PromotionSubscriber {

    public void handleMessage(byte[] message) {
        System.out.println("Received <" + message + ">");
    }
}
