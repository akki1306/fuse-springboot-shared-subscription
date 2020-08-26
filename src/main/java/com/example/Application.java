/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.jms.JMSContext;
import javax.jms.JMSException;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    AMQPComponent amqp() {
        JmsConnectionFactory qpid = new JmsConnectionFactory("admin", "admin", "amqp://localhost:5673");
        AMQPComponent amqpComponent = new AMQPComponent(qpid);
        amqpComponent.setAcknowledgementModeName("CLIENT_ACKNOWLEDGE");
        return amqpComponent;
    }


    @Bean
    public JmsComponent jmsComponent() throws JMSException {
        // Create the connectionfactory which will be used to connect to Artemis
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("(tcp://localhost:61616,tcp://localhost:61716)?initialReconnectDelay=100");
        cf.setUser("admin");
        cf.setPassword("admin");
        JmsComponent jms = new JmsComponent();
        jms.setConnectionFactory(cf);
        return jms;
    }

}