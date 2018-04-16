package com.awesome.backoutq.handler;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class BackoutQListener {
    
    public static final String Q_NAME = "Q_BACKOUT_MSG";
    public static final String LISTENER_ID = "backoutQListener";
    private static final Logger LOGGER = LoggerFactory.getLogger(BackoutQListener.class);
    
    @Autowired
    private BackoutMessageRepository repository;

    
    @Transactional
    @JmsListener (
            id = LISTENER_ID,
            destination = Q_NAME, 
            containerFactory = "jmsListenerContainerFactory"
    )
    public void onMessage(Message message) throws JMSException {
        LOGGER.info("onMessage - Message: {}", message);
        TextMessage textMessage = (TextMessage) message;
        
        byte[] data = textMessage.getText().getBytes();
        repository.save(new BackoutMessage(Q_NAME, new Binary(data)));
        
        LOGGER.info("{} messages", repository.findAll().size());

    }

}
