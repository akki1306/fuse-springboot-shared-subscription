package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SecondSubscriberRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //from("amqp:queue:myqueue?subscriptionDurable=true&durableSubscriptionName=anotherSDS&subscriptionShared=true")
        from("amqp:queue:myqueue")
                .log("Received message in the first route " + body())
                .process(e -> Thread.sleep(25000))
                .log("Finished Processing Message " +body() );
    }
}
