package com.seungse.amadda.adapter.in.web;

import com.seungse.amadda.adapter.out.persistance.ChatRoomRepository;
import com.seungse.amadda.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/room")
    public String chatRoom() {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam(value = "name") String name) {
        return chatRoomRepository.save(name);
    }

    /**
     * Enter a chat room by roomId.
     * @param roomId the ID of the chat room to enter
     * @return the view name for the chat room
     */
    @GetMapping("/room/enter/{roomId}")
    public String enterRoom(@PathVariable(value = "roomId") String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }


    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom getRoom(@PathVariable(value = "roomId") String roomId) {
        return chatRoomRepository.findChatRoom(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found: " + roomId));
    }

}
