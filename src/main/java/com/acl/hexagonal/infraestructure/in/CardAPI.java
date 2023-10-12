package com.acl.hexagonal.infraestructure.in;


import com.acl.hexagonal.application.CardUseCase;
import com.acl.hexagonal.domain.Card;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardAPI {

    private final CardUseCase cardUseCase;

    public CardAPI(CardUseCase cardUseCase) {
        this.cardUseCase = cardUseCase;
    }


    @GetMapping("/{cardNumber}")
    public Card getCardByCardNumber(@PathVariable String cardNumber) {
        return cardUseCase.getCardByCardNumber(cardNumber);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardUseCase.getAllCards();
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardUseCase.createCard(card);
    }

    @PutMapping("/{cardNumber}")
    public void updateCard(@PathVariable String cardNumber, @RequestBody Card card) {
        cardUseCase.updateCard( cardNumber,card);
    }

    @DeleteMapping("/{cardNumber}")
    public void deleteCard(@PathVariable String cardNumber) {
        cardUseCase.deleteCard(cardNumber);
    }
}
