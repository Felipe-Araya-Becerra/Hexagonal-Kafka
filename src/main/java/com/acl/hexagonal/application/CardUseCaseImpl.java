package com.acl.hexagonal.application;

import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardUseCaseImpl implements CardUseCase{

    @Autowired
    private final CardRepository cardRepository;

    public CardUseCaseImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card createCard(Card card) {
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
