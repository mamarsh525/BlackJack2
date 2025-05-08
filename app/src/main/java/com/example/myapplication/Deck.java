package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>(52);
    private static final String[] suits = new String[] {"Hearts","Diamonds","Clubs","Spades"};

    public Deck() {
        generateDeck();
        shuffle();
    }

    public Deck(Random r){
        generateDeck();
        shuffle(r);
    }

    private void generateDeck(){
        //loops through every suit and for every suit it loops 13 times (makes a card for every rank and suit)
        for(int i = 0; i < suits.length; i++) {
            for(int k = 1; k <= 13; k++) {
                cards.add(new Card(k, suits[i]));
            }
        }

    }
    public Card removeCard() {
        return cards.removeLast();
    }

    public ArrayList<Card> getDeck() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void shuffle(Random r){
        Collections.shuffle(cards, r);
        System.out.println("Bottom to top of Deck:");
        for(int i = 0; i < cards.size(); i++) {
            System.out.println(i + 1 + ": " + cards.get(i));
        }
        System.out.println("Top of Deck (What is being pulled by hand constructor and getHit): \n");
    }

}