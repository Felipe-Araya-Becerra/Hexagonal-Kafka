package com.acl.hexagonal;

import com.acl.hexagonal.domain.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testValidCardCreation() {
        Card card = new Card("1234567890123456", "John Doe", 100.0);
        assertNotNull(card);
        assertEquals("1234567890123456", card.getCardNumber());
        assertEquals("John Doe", card.getOwnerName());
        assertEquals(100.0, card.getBalance(), 0.001);
    }


}