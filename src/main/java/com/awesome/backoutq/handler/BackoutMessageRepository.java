package com.awesome.backoutq.handler;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BackoutMessageRepository extends MongoRepository<BackoutMessage, String> {

}
