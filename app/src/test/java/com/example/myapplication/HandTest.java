package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HandTest {
    Hand h1;
    Deck d1;
    Random r;

    @Before
    public void setUp() {
        r = new Random(1);
        d1 = new Deck(r);
        h1 = new Hand(d1);
    }

    @Test
    public void getHit() {
        Card c1 = h1.getHit();
        assertEquals(new Card(9, "Spades"), c1);
    }

    @Test
    public void getCards() {
        // TODO: add test logic
    }

    @Test
    public void calculateScore() {
        assertEquals(15, h1.calculateScore());
    }

    @Test
    public void hasBlackJack() {
        assertFalse(h1.hasBlackJack()); // score: Ace (11) + 10 + 9 = 29, then reduce Ace to 1, so total = 19
        // TODO: add assertTrue case
    }
}
