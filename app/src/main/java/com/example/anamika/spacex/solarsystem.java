package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class solarsystem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solarsystem);
    }
    public void methodF(View v)
    {
        Intent intent = new Intent(getApplicationContext(), play_video.class);
        startActivity(intent);
    }
}
