package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConstants;
import org.springframework.stereotype.Component;

@Component
public class FirstSubscriberRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:topic:mytopic?subscriptionDurable=true&durableSubscriptionName=hello123&subscriptionShared=true")
                .log("Received message in First Subscriber");
    }
}
