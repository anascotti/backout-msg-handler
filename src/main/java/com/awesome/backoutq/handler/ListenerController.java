package com.awesome.backoutq.handler;

import static com.awesome.backoutq.handler.BackoutQListener.LISTENER_ID;
import static com.awesome.backoutq.handler.BackoutQListener.Q_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/listeners/")
public class ListenerController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerController.class);
    
    @Autowired
    private JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    @RequestMapping(value = Q_NAME + "/stop", method = RequestMethod.POST)
    public ResponseEntity<Void> stop() {
        LOGGER.info("Stop listening to {}", Q_NAME);
        
        jmsListenerEndpointRegistry.getListenerContainer(LISTENER_ID).stop();;
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(value = Q_NAME + "/start", method = RequestMethod.POST)
    public ResponseEntity<Void> start() {
        LOGGER.info("Start listening to {}", Q_NAME);
        
        jmsListenerEndpointRegistry.getListenerContainer(LISTENER_ID).start();;
        return ResponseEntity.ok().build();
    }
}
