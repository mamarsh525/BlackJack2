package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        Random r = new Random(42);
        game = new Game(r);
    }

    @Test
    public void testInitialHandSetup() {
        // Manually simulate initial deal using seeded Game constructor
        Card player1 = game.getPlayerHand().getHit(); // Ace of Clubs -> "ca"
        Card player2 = game.getPlayerHand().getHit(); // 7 of Spades -> "s7""

        Card dealer1 = game.getDealerHand().getHit(); // 10 of Spades -> "s10"
        Card dealer2 = game.getDealerHand().getHit(); // 6 of Clubs -> "c6"
        dealer2.flip(); // mimic the default constructor's behavior

        assertEquals("ca", player1.getImageName());
        assertEquals("s7", player2.getImageName());

        assertEquals("s10", dealer1.getImageName());
        assertEquals("c6", dealer2.getImageName());

        assertTrue(dealer2.isFaceUp());
    }



    @Test
    public void testInitialHandHasNoBlackjack() {
        assertFalse(game.checkInitialHand()); // 11 + 7 ≠ 21 and 10 + 6 ≠ 21
    }

    @Test
    public void testDealerPlaysUntilBust() {
        //initial deal
        game.getPlayerHand().getHit(); // Ace of Clubs
        game.getPlayerHand().getHit(); // 7 of Spades

        game.getDealerHand().getHit(); // 10 of Spades
        Card dealerSecond = game.getDealerHand().getHit(); // 6 of Clubs
        dealerSecond.flip(); // matches default constructor behavior

        game.dealersTurn(); // this should draw d6 -> total = 11 + 7 + 6 = 24

        int dealerScore = game.getDealerScore();
        assertTrue(dealerScore > 21); // Dealer should bust
    }

    @Test
    public void testCheckWinnerDealerBustsAndPlayerWins() {
        // Simulate player and dealer initial hands
        game.getPlayerHand().getHit(); // Ace of Clubs (11)
        game.getPlayerHand().getHit(); // 7 of Spades (7) → total 18

        game.getDealerHand().getHit(); // 10 of Spades
        game.getDealerHand().getHit(); // 6 of Clubs → total 16
        game.getDealerHand().getCards().get(1).flip(); // face-down in UI

        game.dealersTurn(); // dealer draws 8 of Diamonds → busts

        String result = game.checkWinner(); // player 18 vs dealer 24
        assertEquals("win", result);
    }


}
