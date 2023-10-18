package com.acl.hexagonal;
import com.acl.hexagonal.application.CardUseCaseImpl;
import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.ports.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class CardUseCaseImplTest {

    @InjectMocks
    private CardUseCaseImpl cardUseCase;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCard() {
        Card card = new Card("1234567890123456", "John Doe", 100.0);
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card createdCard = cardUseCase.createCard(card);

        assertNotNull(createdCard);
        assertEquals("1234567890123456", createdCard.getCardNumber());
        assertEquals("John Doe", createdCard.getOwnerName());
        assertEquals(100.0, createdCard.getBalance(), 0.001);
    }

    @Test
    public void testGetCardByCardNumber() {
        Card card = new Card("1234567890123456", "John Doe", 100.0);
        when(cardRepository.findByCardNumber("1234567890123456")).thenReturn(card);

        Card retrievedCard = cardUseCase.getCardByCardNumber("1234567890123456");

        assertNotNull(retrievedCard);
        assertEquals("1234567890123456", retrievedCard.getCardNumber());
        assertEquals("John Doe", retrievedCard.getOwnerName());
        assertEquals(100.0, retrievedCard.getBalance(), 0.001);
    }

    @Test
    public void testGetAllCards() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card("1234567890123456", "John Doe", 100.0));
        cardList.add(new Card("9876543210987654", "Jane Smith", 200.0));

        when(cardRepository.findAll()).thenReturn(cardList);

        List<Card> allCards = cardUseCase.getAllCards();

        assertNotNull(allCards);
        assertEquals(2, allCards.size());
    }

    @Test
    public void testUpdateCard() {
        Card card = new Card("1234567890123456", "John Doe", 100.0);
        cardUseCase.updateCard("1234567890123456", card);

        verify(cardRepository).update("1234567890123456", card);
    }

    @Test
    public void testDeleteCard() {
        cardUseCase.deleteCard("1234567890123456");
        verify(cardRepository).delete("1234567890123456");
    }
}

