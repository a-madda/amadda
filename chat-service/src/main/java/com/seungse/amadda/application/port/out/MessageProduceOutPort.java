package com.seungse.amadda.application.port.out;

import com.seungse.amadda.domain.ChatMessage;

/**
 * 메시지를 produce하는 아웃바운드 포트
 * 이 포트는 메시지를 외부 시스템(예: Kafka, RabbitMQ 등)으로 전송하는 역할을 합니다.
 *
 * @author seunggu.lee
 * @since 1.0
 */
public interface MessageProduceOutPort {

    void produceMessage(ChatMessage chatMessage);

}
