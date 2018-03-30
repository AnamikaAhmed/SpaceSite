package com.example.anamika.spacex;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.spark.submitbutton.SubmitButton;


public class MainActivity extends AppCompatActivity {
    Handler setDelay;
    Runnable startDelay;
    SubmitButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (SubmitButton) findViewById(R.id.btn3);

        setDelay = new Handler();


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startDelay = new Runnable() {

                    public void run() {

                        Intent intent = new Intent(getApplicationContext(), avatar.class);
                        startActivity(intent);
                        // what you wanna do after sometime
                    }

                };
                setDelay.postDelayed(startDelay, 5000);

            }
        });

    }
}

