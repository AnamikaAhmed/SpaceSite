package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class story2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story2);
    }

    public void methodB(View v)
    {
        Intent intent = new Intent(getApplicationContext(), story3.class);
        startActivity(intent);
    }

}
