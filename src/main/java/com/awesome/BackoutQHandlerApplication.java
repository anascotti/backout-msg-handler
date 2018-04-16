package com.awesome;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@SpringBootApplication
public class BackoutQHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackoutQHandlerApplication.class, args);
    }

    @Bean
    public ConnectionFactory internalConnectionFactory() {
        MQConnectionFactory connectionFactory = new MQConnectionFactory();

        try {
            connectionFactory.setHostName("localhost");
            connectionFactory.setPort(Integer.parseInt("1501"));
            connectionFactory.setQueueManager("QMDOCKER");
            connectionFactory.setChannel("CH1");
            connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            connectionFactory.setClientReconnectOptions(WMQConstants.WMQ_CLIENT_RECONNECT);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        return connectionFactory;
    }
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(internalConnectionFactory());
        factory.setConcurrency("4-4");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setAutoStartup(false);
//        factory.setErrorHandler(queueListenerErrorHandler());
        return factory;
    }
}
