package com.acl.hexagonal.infraestructure.out.ports;

import com.acl.hexagonal.domain.Card;

public interface KafkaConsumerPort {
    public void getMessage(Card card);
}
