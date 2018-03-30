package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class levelactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelactivity);
    }

    public void methodE(View v)
    {
        Intent intent = new Intent(getApplicationContext(), solarsystem.class);
        startActivity(intent);
    }
}
