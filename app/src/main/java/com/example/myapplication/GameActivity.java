package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        //disable the buttons
        Button hitButton = findViewById(R.id.btnHit);
        Button standButton = findViewById(R.id.btnStand);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);

//      start the game
        Game game = new Game();
        updatePlayerScore(game); //updates the text box for the player (this can handle card logic in future)
        updateDealerScore(game); //updates text box for dealer

        //check the initial hand for a winner (user, dealer, or both have black jack)
        if(!game.checkInitialHand()) {
            playersTurn(game);//continue the game (players turn will call the dealers turn when the player has ended their turn)
        }

    }

    private void playersTurn(Game game){
        //returns true if the game continues to dealers turn (player does not bust)
        //false if the player busts (scores greater than 21)

        Button hitButton = findViewById(R.id.btnHit);
        Button standButton = findViewById(R.id.btnStand);
        hitButton.setEnabled(true);
        standButton.setEnabled(true);

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle hit logic here
                game.playerHit();
                updatePlayerScore(game);
                if(game.playerBust()){
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                    results(game);
                }
            }
        });

        standButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
                game.dealersTurn();
                updateDealerScore(game);
                results(game);
            }
        }));

    }

    public void updatePlayerScore(Game game){
        //handles the logic when the players score/hand is updated
        TextView playerScoreText = findViewById(R.id.playerScoreText);
        String message = Integer.toString(game.getPlayerScore());
        playerScoreText.setText(message);

    }

    public void updateDealerScore(Game game){
        TextView dealerScoreText = findViewById(R.id.dealerScoreText);
        String message = Integer.toString((game.getDealerScore()));
        dealerScoreText.setText(message);
    }

    private void results(Game game) {
        game.checkWinner();
        String result = game.getResult();
        Intent intent = new Intent(GameActivity.this, WinLoseActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("playerScore", game.getPlayerScore());
        intent.putExtra("dealerScore", game.getDealerScore());
        startActivity(intent);
    }

}