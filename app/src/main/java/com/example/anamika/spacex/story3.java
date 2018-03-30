package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class story3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story3);
    }


    public void methodC(View v)
    {
        Intent intent = new Intent(getApplicationContext(), helpscreen.class);
        startActivity(intent);
    }
}
