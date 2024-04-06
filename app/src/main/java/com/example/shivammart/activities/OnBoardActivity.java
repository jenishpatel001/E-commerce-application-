package com.example.shivammart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shivammart.R;
import com.example.shivammart.adapters.SlidAdapter;

public class OnBoardActivity extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;
    SlidAdapter slidAdapter;

    Button button;
    TextView[] dots;

    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//hide status bar
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView( R.layout.activity_on_board );
        //hide toolbar

//         getSupportActionBar().hide();
        viewPager = findViewById( R.id.slider );
        dotsLayout = findViewById( R.id.dots );
        addDots(0);

        button = findViewById( R.id.get_started_btn );


        viewPager.addOnPageChangeListener( changeListener );
        slidAdapter = new SlidAdapter( this );
        viewPager.setAdapter( slidAdapter );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(OnBoardActivity.this,RegisterActivity.class) );
                finish();
            }
        } );


    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0;i<dots.length;i++){
            dots[i] = new TextView( this );
            dots[i].setText( Html.fromHtml( "&#8226;" ) );
            dots[i].setTextSize( 35 );
            dotsLayout.addView( dots[i] );

        }
        if (dots.length>0){
            dots[position].setTextColor( getResources().getColor( R.color.pink ) );
        }
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots( position );

            if (position == 0) {
                button.setVisibility( View.INVISIBLE );
            }
            else if(position == 1){
                button.setVisibility( View.INVISIBLE );
            }else
            {
                animation = AnimationUtils.loadAnimation( OnBoardActivity.this,R.anim.slide_animation );
                button.setAnimation( animation );
                button.setVisibility( View.VISIBLE );
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}