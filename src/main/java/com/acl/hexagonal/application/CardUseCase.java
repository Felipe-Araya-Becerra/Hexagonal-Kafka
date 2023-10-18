package com.acl.hexagonal.application;

import com.acl.hexagonal.domain.Card;

import java.util.List;

public interface CardUseCase {
    Card createCard(Card card);

    Card saveCard(Card card);
    Card getCardByCardNumber(String cardNumber);
    List<Card> getAllCards();
    void updateCard(String cardNumber ,Card card);
    void deleteCard(String cardNumber);
}
