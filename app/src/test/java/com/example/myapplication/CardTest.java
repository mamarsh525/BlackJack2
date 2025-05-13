package com.example.myapplication;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardTest {
    @Test
    public void testGetName() {
        assertEquals("7", new Card(7, "Hearts").getName());
        // Test each of the string names
        assertEquals("Ace", new Card(1, "Hearts").getName());
        assertEquals("Jack", new Card(11, "Spades").getName());
        assertEquals("Queen", new Card(12, "Diamonds").getName());
        assertEquals("King", new Card(13, "Clubs").getName());
    }

    @Test
    public void testGetValue() {
        assertEquals(5, new Card(5, "Hearts").getValue());
        assertEquals(11, new Card(1, "Hearts").getValue());     // Aces value gets set to 11
        assertEquals(10, new Card(11, "Clubs").getValue());     // Jack ranks 11, but value 10 bc blackjack rules
        assertEquals(10, new Card(12, "Spades").getValue());    // Queen ranks 12, but value 10
        assertEquals(10, new Card(13, "Diamonds").getValue());  // King ranks 13, but value 10
    }

    @Test
    public void testEquals() {
        Card c1 = new Card(10, "Hearts");
        Card c2 = new Card(10, "Hearts");
        Card c3 = new Card(10, "Spades");

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

    @Test
    public void testToString() {
        assertEquals("King of Spades", new Card(13, "Spades").toString());
        assertEquals("2 of Clubs", new Card(2, "Clubs").toString());
    }
}

