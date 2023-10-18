package com.acl.hexagonal;

import com.acl.hexagonal.domain.Card;
import com.acl.hexagonal.infraestructure.out.adapters.CardRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CardRepositoryImplTest {

    @InjectMocks
    private CardRepositoryImpl cardRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Card card = new Card("1234567890123456", "John Doe", 100.0);
        when(jdbcTemplate.update(any(String.class), any(Object.class))).thenReturn(1);

        Card savedCard = cardRepository.save(card);

        verify(jdbcTemplate).update(
                "INSERT INTO cards (card_number, owner_name, balance) VALUES (?, ?, ?)",
                card.getCardNumber(), card.getOwnerName(), card.getBalance()
        );
    }

    @Test
    public void testFindByCardNumber() {
        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(Class.class)))
                .thenReturn(new Card("1234567890123456", "John Doe", 100.0));

        Card card = cardRepository.findByCardNumber("1234567890123456");

        verify(jdbcTemplate).queryForObject(
                "SELECT card_number, owner_name, balance FROM cards WHERE card_number = ?",
                new Object[]{"1234567890123456"},
                Card.class
        );
    }

    @Test
    public void testFindAll() {
        when(jdbcTemplate.query(any(String.class), any(ResultSetExtractor.class)))
                .thenReturn(List.of(new Card("1234567890123456", "John Doe", 100.0)));

        List<Card> cardList = cardRepository.findAll();

        assertEquals(1, cardList.size());
        Card card = cardList.get(0);
        assertEquals("1234567890123456", card.getCardNumber());
        assertEquals("John Doe", card.getOwnerName());
        assertEquals(100.0, card.getBalance(), 0.01);
    }

    @Test
    public void testUpdate() {
        Card card = new Card("1234567890123456", "Jane Smith", 200.0);
        cardRepository.update("1234567890123456", card);

        verify(jdbcTemplate).update(
                "UPDATE cards SET owner_name = ?, balance = ? WHERE card_number = ?",
                card.getOwnerName(), card.getBalance(), "1234567890123456"
        );
    }

    @Test
    public void testDelete() {
        cardRepository.delete("1234567890123456");

        verify(jdbcTemplate).update(
                "DELETE FROM cards WHERE card_number = ?",
                "1234567890123456"
        );
    }
}
