package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void generateDeck() {
        assertEquals(52, deck.getDeck().size());
        HashSet<String> cardSet = new HashSet<>();
        for (Card c : deck.getDeck()) {
            cardSet.add(c.getName() + " of " + c.getSuit());
        }
        assertEquals(52, cardSet.size());         // deck should have 52 cards
    }

    @Test
    public void removeCard() {
        int initialSize = deck.getDeck().size();
        Card cardToRemove = deck.removeCard();
        assertEquals(initialSize - 1, deck.getDeck().size());
        assertFalse(deck.getDeck().contains(cardToRemove));
    }

    @Test
    public void shuffle() {
        ArrayList<Card> original = new ArrayList<>(deck.getDeck()); // copy original deck before shuffling
        deck.shuffle();
        ArrayList<Card> shuffled = deck.getDeck();
        // TODO: what if perfect shuffle??
        assertNotEquals(original, shuffled);    // deck should be in different order after shuffle
        assertEquals(52, shuffled.size()); // shuffled deck still same size
    }

    @Test
    public void getDeck() {
        assertNotNull(deck.getDeck());
        assertEquals(52, deck.getDeck().size());
    }
}

