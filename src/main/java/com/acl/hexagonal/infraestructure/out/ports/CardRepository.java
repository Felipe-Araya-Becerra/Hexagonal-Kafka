package com.acl.hexagonal.infraestructure.out.ports;

import com.acl.hexagonal.domain.Card;

import java.util.List;

public interface CardRepository {
    Card save(Card card);
    Card findByCardNumber(String cardNumber);
    List<Card> findAll();
    void update(String cardNumber, Card card);
    void delete(String cardNumber);
}
