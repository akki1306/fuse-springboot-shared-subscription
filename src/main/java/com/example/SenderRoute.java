package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConstants;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class SenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://foo?fixedRate=true&period=5000")
                .process(e -> e.getIn().setBody(new Date().toString()))
                // set header so that only one subscriber receives the message
                //.setHeader(JmsConstants.JMS_X_GROUP_ID,simple("onlyOne"))
                .to("amqp:queue:myqueue");
    }
}
