package com.example.myapplication;

import java.util.Random;
import java.util.Scanner;

public class Game{

    private static int winCount;
    private static int loseCount;
    private static int tieCount;

    private Hand dealerHand;
    private Hand playerHand;
    private Deck currentDeck;

    static {
        winCount = 0;
        loseCount = 0;
        tieCount = 0;
    }

    public Game(){
        currentDeck = new Deck();
        currentDeck.shuffle();
        playerHand = new Hand(currentDeck);
        dealerHand = new Hand(currentDeck);

        // players initall hand
        playerHand.getHit();
        playerHand.getHit();

        // dealers initial hand
        Card dealerFirstCard = dealerHand.getHit();
        Card dealerSecondCard = dealerHand.getHit();
        dealerSecondCard.flip();

    }

    public Game(Random r){
        currentDeck = new Deck();
        currentDeck.shuffle(r);
        playerHand = new Hand(currentDeck);
        dealerHand = new Hand(currentDeck);
    }

    public void quit(){
        return;
    }

    public boolean checkInitialHand(){
        //determines if the player or dealer have won with a black jack on the initial deal
        if(playerHand.hasBlackJack() && dealerHand.hasBlackJack()){
            tieCount++;//tie
            return true;
        } else if (playerHand.hasBlackJack() && !dealerHand.hasBlackJack()) {
            winCount++;
            return true;
        }else if (dealerHand.hasBlackJack() && !playerHand.hasBlackJack()){
            loseCount++;
            return true;
        }
        return false;
    }

    public String checkWinner(){
        int playerScore = playerHand.calculateScore();
        int dealerScore = dealerHand.calculateScore();

        if (playerScore > 21) {
            loseCount++;
            return "lose";
        } else if (dealerScore > 21) {
            winCount++;
            return "win";
        } else if (playerScore > dealerScore) {
            winCount++;
            return "win";
        } else if (playerScore < dealerScore) {
            loseCount++;
            return "lose";
        } else {
            tieCount++;
            return "draw";
        }
    }

    public void playersTurn(){
        if(playerHand.getCards().size() != 2 || dealerHand.getCards().size() != 2){
            return; //should not happen
        }
        Scanner in = new Scanner(System.in);
        while(!playerHand.scoreBusts()){
            //will probably look much different in android
            System.out.println("Score: " + playerHand.calculateScore());
            System.out.println("Hit (h) or stand (s): ");
            String input = in.nextLine().trim().toLowerCase();
            if(input.equals("h")){
                playerHand.getHit();
                System.out.println("Hit, score = " + playerHand.calculateScore());
            }else if (input.equals("s")){
                System.out.println("Stand");
                break;
            }else{
                System.out.println("Input h or s");
            }
        }
    }

    public String playerHit(){
        if (playerHand.scoreBusts()){
            return null; //should not happen
        }
        Card newCard = playerHand.getHit();
        return newCard.getImageName();
    }

    public boolean playerBust(){
        return playerHand.scoreBusts();
    }

    public int getPlayerScore(){
        return playerHand.calculateScore();
    }

    public void dealersTurn(){
        if (dealerHand.getCards().size() != 2 ){
            return; //should not happen
        }
        // flip the second card
        Card secondCard = dealerHand.getCards().get(1);
        secondCard.flip();

        while(!dealerHand.scoreBusts() && dealerHand.calculateScore() < 17){
            dealerHand.getHit();
        }
    }

    public int getDealerScore(){
        return dealerHand.calculateScore();
    }

    public Hand getPlayerHand(){
        return playerHand;
    }
    public Hand getDealerHand(){
        return dealerHand;
    }

}