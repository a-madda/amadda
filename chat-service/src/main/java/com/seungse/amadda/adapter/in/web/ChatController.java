package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {


    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {
        if(MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(), chatMessage);
    }

}
