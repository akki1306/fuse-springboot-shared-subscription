package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConstants;
import org.springframework.stereotype.Component;

//@Component
public class FirstSubscriberRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       // from("amqp:queue:myqueue?subscriptionDurable=true&subscriptionName=anotherSDS&subscriptionShared=true")
        from("amqp:queue:myqueue")
                .log("Received message in the first route " + body())
                .process(e -> Thread.sleep(20000))
                .log("Finished Processing Message " +body() );
    }
}
