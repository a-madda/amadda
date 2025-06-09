package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.ChatRoomEntity;
import com.seungse.amadda.infrastructure.repository.ChatRoomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomPostgresRepository extends ChatRoomRepository<ChatRoomEntity, Long>, JpaRepository<ChatRoomEntity, Long> {

    @Override
    List<ChatRoomEntity> findAllByParticipantId(Long participantId);

}
