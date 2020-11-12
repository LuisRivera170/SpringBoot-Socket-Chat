package com.springboot.socket.chat.service;

import java.util.List;

import com.springboot.socket.chat.models.documents.Message;

public interface IChatService {

	public List<Message> getLastTenMessages();
	
	public Message save(Message message);
	
}
