package com.awesome.backoutq.handler;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BackoutMessage {
    
    @Id
    private String id;
    private String queue;
    private Binary payload;

    public BackoutMessage(String queue, Binary payload) {
        this.queue = queue;
        this.payload = payload;
    }

    public String getQueue() {
        return queue;
    }

    public Binary getPayload() {
        return payload;
    }

    public String getId() {
        return id;
    }

}
