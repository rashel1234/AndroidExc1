package com.example.class3demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Xmixdrix extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2) {
            counter++;

            gameState[tappedImage] = activePlayer;

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ImageView status = findViewById(R.id.status);

                status.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ImageView status = findViewById(R.id.status);

                status.setImageResource(R.drawable.xplay);
            }
        }

        int flag = 0;
        ImageView status = findViewById(R.id.status);
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                if (gameState[winPosition[0]] == 0) {
                    status.setImageResource(R.drawable.xwin);
                    findViewById(R.id.newGame).setEnabled(true);
                } else {
                    status.setImageResource(R.drawable.owin);
                    findViewById(R.id.newGame).setEnabled(true);
                }

            }
        }
        if (counter == 9 && flag == 0) {
            status.setImageResource(R.drawable.nowin);
            findViewById(R.id.newGame).setEnabled(true);
        }
    }

    public void gameReset(View view) {
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        ImageView status = findViewById(R.id.status);
        status.setImageResource(R.drawable.xplay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmixdrix);

        Button newGame = findViewById(R.id.newGame);
        //newGame.setEnabled(false);
        newGame.setOnClickListener(this::gameReset);

        ((ImageView) findViewById(R.id.status)).setImageResource(R.drawable.xplay);
    }
}