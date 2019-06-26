package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class helpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
