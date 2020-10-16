package com.springboot.socket.chat.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.springboot.socket.chat.models.documents.Message;

@Controller
public class ChatController {

	@MessageMapping("/message")
	@SendTo("/chat/message")
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		return message;
	}
	
}
