package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.in.web.response.ChatMessageResponse;
import com.seungse.amadda.application.port.in.ChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatSearchController {

    private final ChatUseCase chatUseCase;

    @GetMapping("/chats/{roomId}")
    public ResponseEntity<List<ChatMessageResponse>> getChatMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(chatUseCase.getChatMessages(roomId).stream()
                .map(chatMessage -> new ChatMessageResponse(
                        chatMessage.getId(),
                        chatMessage.getMessage(),
                        chatMessage.getSenderId(),
                        chatMessage.getSentAt().toString()
                ))
                .toList());
    }
}
