package com.example.tele6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class List<I extends AppCompatActivity> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}