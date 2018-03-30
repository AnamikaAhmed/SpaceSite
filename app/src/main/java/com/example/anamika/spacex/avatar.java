package com.example.anamika.spacex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class avatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }

    public void methodSlider(View v) {
        Intent intent = new Intent(getApplicationContext(), Slider1.class);
        startActivity(intent);
    }

}
