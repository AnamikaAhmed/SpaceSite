package com.example.anamika.spacex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Slider1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider1);
    }

    public void firstStory(View v) {
        Intent intent = new Intent(getApplicationContext(), story1.class);
        startActivity(intent);
    }
}
