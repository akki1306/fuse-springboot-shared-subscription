package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConstants;
import org.springframework.stereotype.Component;

//@Component
public class SenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://foo?fixedRate=true&period=600")
                .setBody(simple("123"))
                // set header so that only one subscriber receives the message
                .setHeader(JmsConstants.JMS_X_GROUP_ID,simple("onlyOne"))
                .to("jms:topic:mytopic");
    }
}
