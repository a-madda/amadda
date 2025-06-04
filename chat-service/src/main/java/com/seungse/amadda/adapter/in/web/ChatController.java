package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.out.persistance.ChatOutPortAdapter;
import com.seungse.amadda.application.port.in.ChatUseCase;
import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.MessageType;
import com.seungse.amadda.infrastructor.publisher.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatUseCase chatUseCase;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {
        chatUseCase.sendMessage(chatMessage);
    }

}
