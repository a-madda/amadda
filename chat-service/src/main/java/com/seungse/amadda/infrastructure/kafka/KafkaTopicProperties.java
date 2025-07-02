package com.seungse.amadda.infrastructure.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.kafka.topic")
public class KafkaTopicProperties {

    private String chat;
    private String chatRoom;
    private String group;

}
