package com.example.smarttravelguideapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.smarttravelguideapplication.Adapter.SliderAdapter;
import com.example.smarttravelguideapplication.LoginSystem.LoginActivity;

public class OnboardingActivity extends AppCompatActivity {



    ViewPager viewPager;
    LinearLayout dotsLayout;


    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted,skip_btn;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //binding
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        skip_btn = findViewById(R.id.skip_btn);

        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        Intent i = new Intent(OnboardingActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPos = position;
            if (position == 0){
                letsGetStarted.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            }
            else if(position == 1){
                letsGetStarted.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            }
            else if(position == 2){
                letsGetStarted.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            }
            else{
                animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);

                Animation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                fadeOut.setStartOffset(1000);
                fadeOut.setDuration(1000);

                AnimationSet skipAnimation = new AnimationSet(false);
                skipAnimation.addAnimation(fadeOut);
                skip_btn.setAnimation(skipAnimation);
                skip_btn.setVisibility(View.INVISIBLE);

                letsGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(OnboardingActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}