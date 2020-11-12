package com.springboot.socket.chat.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.socket.chat.models.documents.Message;

public interface ChatRepository extends MongoRepository<Message, String> {
	
	public List<Message> findFirst10ByOrderByDateDesc();
	
}
