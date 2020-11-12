   package com.springboot.socket.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.springboot.socket.chat.models.documents.Message;

@Controller
public class ChatController {

	private String[] colors = {"red", "green", "blue", "magenta", "purple", "orange"};
	
	@MessageMapping("/message")
	@SendTo("/chat/message")
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		
		if (message.getType().equals("NEW_USER")) {
			message.setText("Nuevo usuario");
			message.setColor(colors[new Random().nextInt(colors.length)]);
		}
		return message;
	}
	
	@MessageMapping("/writing")
	@SendTo("/chat/writing")
	public String writingMessage(String username) {
		return username.concat(" está escribiendo...");
	}
	
	
}
