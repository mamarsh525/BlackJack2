package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinLoseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_lose);

        Button playAgainButton = findViewById(R.id.ReplayButton);
        Button MainMenuButton = findViewById(R.id.MainMenuButton);
        TextView winLoseText = findViewById(R.id.WinLoseText);
        TextView playerScoreText = findViewById(R.id.PlayerScoreText);
        TextView dealerScoreText = findViewById(R.id.DealerScoreText);

        MainMenuButton.setEnabled(true);
        playAgainButton.setEnabled(true);

        String result = getIntent().getStringExtra("result");
        int playerScore = getIntent().getIntExtra("playerScore", -1);
        int dealerScore = getIntent().getIntExtra("dealerScore", -1);

        if (result != null) {
            switch (result) {
                case "win":
                    winLoseText.setText("You Won! (somehow)");
                    break;
                case "lose":
                    winLoseText.setText("You Lose! Better luck next time!");
                    break;
                case "draw":
                    winLoseText.setText("No one wins... draw!");
                    break;
            }
        }
        playerScoreText.setText("Your Score: " + playerScore);
        dealerScoreText.setText("Dealer Score: " + dealerScore);

        MainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinLoseActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinLoseActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
