package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button twoPlayer;
    private Button helpScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPlayer = findViewById(R.id.twoPlayer);
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();
            }
        });

        helpScreen = findViewById(R.id.help);
        helpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelpScreen();
            }
        });

    }

    private void openGame() {
        Intent intent = new Intent(this, connectFourGame.class);
        startActivity(intent);
    }

    private void openHelpScreen() {
        Intent intent = new Intent(this, helpScreen.class);
        startActivity(intent);
    }

}
