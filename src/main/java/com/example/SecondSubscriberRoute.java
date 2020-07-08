package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SecondSubscriberRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:topic:mytopic?subscriptionDurable=true&durableSubscriptionName=hello123&subscriptionShared=true")
                .log("Received message in Second Subscriber");
    }
}
