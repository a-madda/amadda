package com.seungse.amadda.infrastructure.repository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository<T, E> {

    List<T> findAllByParticipantId(Long participantId);

    /**
     * 채팅방을 생성합니다.
     *
     * @param chatRoom 채팅방 정보
     * @return 생성된 채팅방
     */
    T save(T chatRoom);

    /**
     * 채팅방을 조회합니다.
     *
     * @param chatRoomId 채팅방 ID
     * @return 조회된 채팅방
     */
    Optional<T> findById(E chatRoomId);

}
