package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class Card{
    private int rank;
    private String suit;
    private boolean isFaceUp;

    // hashmap to store the rank name of the card and the actual value; made to address face cards
    private static final Map<String, Integer> values = new HashMap<>();

    // made static so only one copy of this map will be shared and used by all
    // other instances of card, saving memory.
    static {
        values.put("2", 2);
        values.put("3", 3);
        values.put("4", 4);
        values.put("5", 5);
        values.put("6", 6);
        values.put("7", 7);
        values.put("8", 8);
        values.put("9", 9);
        values.put("10", 10);
        values.put("Jack", 10);
        values.put("Queen", 10);
        values.put("King", 10);
        values.put("Ace", 11);
    }

    public Card(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank(){
        return rank;
    }

    public String getSuit(){
        return suit;
    }

    // method to get the rank of the card as a string (specifically made for face cards)
    public String getName(){
        switch(rank){
            case 1: return "Ace";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            default: return Integer.toString(rank); // converts int to string
        }
    }

    public int getValue(){
        String name = getName();
        return values.get(name);
    }

    public String toString(){
        return getName() + " of " + getSuit();
    }

    // used generate code method tool; used for comparing if two card objects are equaled
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Card)) return false;
        Card c = (Card)o;
        return getRank() == c.getRank() && getSuit().equals(c.getSuit());
    }

    public String getImageName() {
        String suitLetter = suit.substring(0, 1);
        String rankStr;

        switch(rank) {
            case 1: rankStr = "a"; break;
            case 11: rankStr = "j"; break;
            case 12: rankStr = "q"; break;
            case 13: rankStr = "k"; break;
            default: rankStr = Integer.toString(rank); break;
        }

        return suitLetter + rankStr;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flip() {
        this.isFaceUp = !this.isFaceUp;
    }

}
