package com.example.myapplication;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private Deck deck;


    public Hand(Deck deck) {
        this.deck = deck;
        cards = new ArrayList<Card>();
    }

    public Card getHit() {
        //getHit will take in a card, add it to the hand and update the score
        Card hitCard = deck.removeCard();
        cards.add(hitCard);
        return hitCard;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int sum = 0;
        int aceCount =0;
        for(Card card : cards) {
            sum += card.getValue();
            if(card.getName().equals("Ace")){
                aceCount++;
            }
        }
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    public boolean hasBlackJack(){
        if (cards.size() != 2){
            return false;
        }

        String firstCard = cards.get(0).getName();
        String secondCard = cards.get(1).getName();

        if(firstCard.equals("Ace") && containsA10Card(secondCard)){
            return true;
        }else if (secondCard.equals("Ace") && containsA10Card(firstCard)){
            return true;
        }
        return false;
    }

    private boolean containsA10Card(String cardName){
        String [] faceCards = {"Jack", "Queen", "King", "10"};
        for(String card : faceCards){
            if(cardName.equals(card)){
                return true;
            }
        }
        return false;
    }

    public boolean scoreBusts(){
        return calculateScore() > 21;
    }

}