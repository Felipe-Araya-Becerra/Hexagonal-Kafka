package com.acl.hexagonal.infraestructure.out.adapters;


import com.acl.hexagonal.application.CardUseCase;
import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.ports.KafkaConsumerPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumerAdapter implements KafkaConsumerPort {


    private final CardUseCase cardUseCase;

    public KafkaConsumerAdapter(CardUseCase cardUseCase) {
        this.cardUseCase = cardUseCase;
    }

    @KafkaListener(topics = "card-topic" , groupId = "myGroup")
    public void getMessage(Card card){
        cardUseCase.saveCard(card);
    }


}
