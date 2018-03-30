package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class helpscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpscreen);
    }

    public void methodD(View v)
    {
        Intent intent = new Intent(getApplicationContext(), changeroom.class);
        startActivity(intent);
    }
}
