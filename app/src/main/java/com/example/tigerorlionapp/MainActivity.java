package com.example.tigerorlionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE,TWO,NO;
    }

    Player currentPlayer = Player.ONE;
    Player[]  playerChoices = new  Player[9];

    int[][] winnerRowsColumns = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},
            {1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private boolean gameOver = false;
    private Button BtnReset;
    private GridLayout gridView;
    private TextView winnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        BtnReset = findViewById(R.id.btnReset);

        gridView = findViewById(R.id.grid_Layout);

        winnerName = findViewById(R.id.winnerName);

        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTheGame();
            }
        });


    }



    public void imageViewIsTapped(View view){
        ImageView tappedImageview = (ImageView) view;
        int tiTag = Integer.parseInt(tappedImageview.getTag().toString());

        if (playerChoices[tiTag] == Player.NO && gameOver == false) {

            tappedImageview.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageview.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageview.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;

            }
            tappedImageview.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(2000);

           // Toast.makeText(this, tappedImageview.getTag().toString(), Toast.LENGTH_LONG).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.NO) {

                    BtnReset.setVisibility(View.VISIBLE);
                    winnerName.setVisibility(View.VISIBLE);

                    gameOver = true;

                    String WinnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        WinnerOfGame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        WinnerOfGame = "Player One";

                    }

                    winnerName.setText(WinnerOfGame +" is The Winner ");
                   // Toast.makeText(this, WinnerOfGame + " is The Winner ", Toast.LENGTH_SHORT).show();
                }
            }
            BtnReset.setVisibility(View.VISIBLE);

        }
    }

    private void ResetTheGame() {

        for (int index = 0; index < gridView.getChildCount(); index++){

            ImageView imageView = (ImageView)gridView.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }

        currentPlayer = Player.ONE;
        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        gameOver = false;

    }
}
