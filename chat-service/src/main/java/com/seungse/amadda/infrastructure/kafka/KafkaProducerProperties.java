package com.seungse.amadda.infrastructure.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.kafka.producer")
public class KafkaProducerProperties {

    private String bootstrapServers;

}
