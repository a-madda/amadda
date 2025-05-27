package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.out.persistance.ChatRoomRepository;
import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.MessageType;
import com.seungse.amadda.infrastructor.publisher.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {
        if(MessageType.ENTER.equals(chatMessage.getType())) {
            chatRoomRepository.enterChatRoom(chatMessage.getRoomId());
           chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        }
        redisPublisher.publish(chatRoomRepository.getTopic(chatMessage.getRoomId()), chatMessage);
    }

}
