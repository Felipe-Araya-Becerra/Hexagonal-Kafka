package com.acl.hexagonal.infraestructure.out.adapters;

import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.ports.KafkaProducerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerAdapter implements KafkaProducerPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerAdapter.class);

    private final KafkaTemplate<String, Card> kafkaTemplate;

    public KafkaProducerAdapter(KafkaTemplate<String, Card> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Card sendMessage(Card data){
        LOGGER.info(String.format("Message Sent -> %s:", data.toString()));

        Message<Card> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "card-topic").build();
        kafkaTemplate.send(message);
        return data;
    }
}
