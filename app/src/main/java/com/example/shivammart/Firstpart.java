package com.example.shivammart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shivammart.adapters.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Firstpart extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.bottle2,
    R.drawable.bottle,
    R.drawable.cooker,
    R.drawable.kadai};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_firstpart );

        sliderView = findViewById( R.id.image_slider );

        SliderAdapter sliderAdapter = new SliderAdapter( images );
        sliderView.setSliderAdapter( sliderAdapter );
        sliderView.setIndicatorAnimation( IndicatorAnimationType.WORM );
        sliderView.setSliderTransformAnimation( SliderAnimations.DEPTHTRANSFORMATION );
        sliderView.startAutoCycle();

    }
}