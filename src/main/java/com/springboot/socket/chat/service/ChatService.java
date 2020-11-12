package com.springboot.socket.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.socket.chat.dao.ChatRepository;
import com.springboot.socket.chat.models.documents.Message;

@Service
public class ChatService implements IChatService {
	
	@Autowired
	private ChatRepository chatDao;

	@Override
	public List<Message> getLastTenMessages() {
		return chatDao.findFirst10ByOrderByDateDesc();
	}

	@Override
	public Message save(Message message) {
		return chatDao.save(message);
	}

	
	
}
