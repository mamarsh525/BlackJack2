package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    LinearLayout playerCardContainer;
    LinearLayout dealerCardContainer;
    Button hitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        playerCardContainer = findViewById(R.id.playerCardContainer);
        dealerCardContainer = findViewById(R.id.dealerCardContainer);

        //disable the buttons
        hitButton = findViewById(R.id.btnHit);
        Button standButton = findViewById(R.id.btnStand);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);

//      start the game
        Game game = new Game();
        updatePlayerScore(game); //updates the text box for the player (this can handle card logic in future)
        updateDealerScore(game, false); //updates text box for dealer

        showInitialCards(game);

        //check the initial hand for a winner (user, dealer, or both have black jack)
        if(!game.checkInitialHand()) {
            playersTurn(game);//continue the game (players turn will call the dealers turn when the player has ended their turn)
        }
        else{
            results(game);
        }

    }
    private void showInitialCards(Game game) {
        // Add two cards for the player
        for (Card card : game.getPlayerHand().getCards()) {
            addCardImage(playerCardContainer, card); // Show player cards
        }

        // Add two cards for the dealer
        // Dealers first card is face-up
        addCardImage(dealerCardContainer, game.getDealerHand().getCards().get(0));

        // Dealers second card is face down
        ImageView dealerCardToImage = new ImageView(this);
        dealerCardToImage.setImageResource(R.drawable.gray_back);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 300);
        params.setMargins(8, 0, 8, 0);
        dealerCardToImage.setLayoutParams(params);

        dealerCardContainer.addView(dealerCardToImage);
    }

    private void playersTurn(Game game) {
        //returns true if the game continues to dealers turn (player does not bust)
        //false if the player busts (scores greater than 21)

        Button hitButton = findViewById(R.id.btnHit);
        Button standButton = findViewById(R.id.btnStand);
        hitButton.setEnabled(true);
        standButton.setEnabled(true);

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerHit();
                Card newCard = game.getPlayerHand().getCards().get(game.getPlayerHand().getCards().size() - 1);
                addCardImage(playerCardContainer, newCard);
                //handle hit logic here
                updatePlayerScore(game);
                if (game.playerBust()) {
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                    results(game);
                }
            }
        });

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);

                // Flip the dealer's hidden card
                ImageView dealerCardToFlip = (ImageView) dealerCardContainer.getChildAt(1);
                dealerCardToFlip.setImageResource(CardImages.getCardResourceId(game.getDealerHand().getCards().get(1).getImageName()));
                updateDealerScore(game, true);

                Handler handler = new Handler(Looper.getMainLooper());
                int delay = 1000;

                Runnable dealerPlay = new Runnable() {
                    @Override
                    public void run() {
                        if (game.getDealerScore() < 17) {
                            game.dealerHit();
                            Card newCard = game.getDealerHand().getCards().get(game.getDealerHand().getCards().size() - 1);
                            addCardImage(dealerCardContainer, newCard);
                            updateDealerScore(game, true);

                            handler.postDelayed(this, delay); // schedule next hit
                        } else {
                            results(game); // dealer is done, show results
                        }
                    }
                };

                handler.postDelayed(dealerPlay, delay); // start the dealer's turn after delay
            }
        });
    }

    public void updatePlayerScore(Game game){
        //handles the logic when the players score/hand is updated
        TextView playerScoreText = findViewById(R.id.playerScoreText);
        String message = Integer.toString(game.getPlayerScore());
        playerScoreText.setText(message);

    }

    public void updateDealerScore(Game game, boolean showFullScore){
        TextView dealerScoreText = findViewById(R.id.dealerScoreText);

        int score;
        if (showFullScore) {
            score = game.getDealerScore();
        } else {
            // Only count the first (face-up) card
            Card firstCard = game.getDealerHand().getCards().get(0);
            score = firstCard.getValue(); // This assumes getValue() returns Blackjack value
        }
        dealerScoreText.setText(String.valueOf(score));
    }

    private void results(Game game) {
        game.checkWinner();
        String result = game.checkWinner();

        ImageView dealerCardToFlip = (ImageView) dealerCardContainer.getChildAt(1);
        dealerCardToFlip.setImageResource(CardImages.getCardResourceId(game.getDealerHand().getCards().get(1).getImageName()));
        updateDealerScore(game, true);
        // delays the transition to make game feel smoother
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()  {
            @Override
            public void run() {
                Intent intent = new Intent(GameActivity.this, WinLoseActivity.class);
                intent.putExtra("result", result);
                intent.putExtra("playerScore", game.getPlayerScore());
                intent.putExtra("dealerScore", game.getDealerScore());
                startActivity(intent);
            }
        }, 1500);
    }
    public void addCardImage(LinearLayout container, Card card) {
        ImageView cardImage = new ImageView(this);

        String cardName = card.getImageName(); // Example: "10h
        int resId = CardImages.getCardResourceId(cardName);

        cardImage.setImageResource(resId);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 300);
        params.setMargins(8, 0, 8, 0);
        cardImage.setLayoutParams(params);

        container.addView(cardImage);
    }
}