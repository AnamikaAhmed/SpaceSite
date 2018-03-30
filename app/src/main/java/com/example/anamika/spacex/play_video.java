package com.example.anamika.spacex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class play_video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
    }

    public void methodQ(View v) {
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(intent);
    }

    public void methodV(View v) {
        Intent intent = new Intent(getApplicationContext(), EasyVideoPlayer.class);
        startActivity(intent);
    }


}

