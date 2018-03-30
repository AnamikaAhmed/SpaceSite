package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class story1 extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story1);


    }

    public void buttonClickFunction(View v)
    {
        Intent intent = new Intent(getApplicationContext(), story2.class);
        startActivity(intent);
    }
}
