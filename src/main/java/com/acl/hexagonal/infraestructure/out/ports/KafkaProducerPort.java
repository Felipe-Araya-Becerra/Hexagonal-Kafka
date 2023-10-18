package com.acl.hexagonal.infraestructure.out.ports;

import com.acl.hexagonal.domain.Card;

public interface KafkaProducerPort {
    public Card sendMessage(Card card);
}
