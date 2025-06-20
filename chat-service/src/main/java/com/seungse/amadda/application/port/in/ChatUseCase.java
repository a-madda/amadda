package com.seungse.amadda.application.port.in;

import com.seungse.amadda.domain.ChatMessage;
import com.seungse.amadda.domain.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatUseCase {

    /**
     * 채팅방 생성
     *
     * @param name 채팅방 이름
     * @return 생성된 채팅방 ID
     */
    ChatRoom createGroupChatRoom(String name);

    /**
     * 채팅방에 참여
     *
     * @param roomId 채팅방 ID
     * @param userId 사용자 ID
     */
    void joinChatRoom(String roomId, String userId);

    /**
     * 채팅 메시지 전송
     *
     */
    void sendMessage(ChatMessage chatMessage);

    /**
     * 채팅방 전체 조회
     * @return
     */
    List<ChatRoom> getAllChatRooms();

    /**
     * 채팅방 조회
     *
     * @param roomId 채팅방 ID
     * @return 채팅방 정보
     */
    Optional<ChatRoom> getChatRoom(String roomId);

}
