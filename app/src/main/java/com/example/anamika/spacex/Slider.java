package com.example.anamika.spacex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.SliderLayout;

/**
 * Created by anamika on 10/5/17.
 */

public class Slider extends AppCompatActivity {

    private SliderLayout sliderLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_layout);

    }
}