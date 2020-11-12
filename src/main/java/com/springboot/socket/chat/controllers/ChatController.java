   package com.springboot.socket.chat.controllers;

import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.springboot.socket.chat.models.documents.Message;
import com.springboot.socket.chat.service.IChatService;

@Controller
public class ChatController {

	private String[] colors = {"red", "green", "blue", "magenta", "purple", "orange"};
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/message")
	@SendTo("/chat/message")
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		
		if (message.getType().equals("NEW_USER")) {
			message.setText("Nuevo usuario");
			message.setColor(colors[new Random().nextInt(colors.length)]);
		} else {
			chatService.save(message);
		}
		return message;
	}
	
	@MessageMapping("/writing")
	@SendTo("/chat/writing")
	public String writingMessage(String username) {
		return username.concat(" está escribiendo...");
	}
	
	@MessageMapping("/record")
	public void recordMessages(String clientId) {
		webSocket.convertAndSend("/chat/record/" + clientId, chatService.getLastTenMessages());
	}
	
}
