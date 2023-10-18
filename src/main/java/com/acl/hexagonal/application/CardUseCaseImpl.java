package com.acl.hexagonal.application;

import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.ports.CardRepository;
import com.acl.hexagonal.infraestructure.out.ports.KafkaConsumerPort;
import com.acl.hexagonal.infraestructure.out.ports.KafkaProducerPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardUseCaseImpl implements CardUseCase{


    private final CardRepository cardRepository;
    private final KafkaProducerPort kafkaProducerPort;

    public CardUseCaseImpl(CardRepository cardRepository, KafkaProducerPort kafkaProducerPort) {
        this.cardRepository = cardRepository;
        this.kafkaProducerPort = kafkaProducerPort;

    }

    @Override
    public Card createCard(Card card) {
        return kafkaProducerPort.sendMessage(card);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public void updateCard(String cardNumber, Card card) {
        cardRepository.update(cardNumber,card);
    }

    @Override
    public void deleteCard(String cardNumber) {
        cardRepository.delete(cardNumber);
    }
}
