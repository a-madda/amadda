package com.seungse.amadda.adapter.out.persistance.repository;

import com.seungse.amadda.adapter.out.persistance.entity.ChatRoomEntity;
import com.seungse.amadda.infrastructure.repository.ChatRoomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomPostgresRepository extends ChatRoomRepository<ChatRoomEntity, Long>, JpaRepository<ChatRoomEntity, Long> {

    /**
     * roomId 가 존재하는지 확인
     * @param roomId
     * @return
     */
    boolean existsByRoomId(UUID roomId);

    Optional<ChatRoomEntity> findByRoomId(UUID roomId);
  
}
