package com.example.smarttravelguideapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttravelguideapplication.LoginSystem.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView splash_img,splash_text;
    Animation side_anim,bottom_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splash_img = findViewById(R.id.splash_img);
        splash_text = findViewById(R.id.splash_text);

        side_anim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        splash_img.setAnimation(side_anim);
        splash_text.setAnimation(bottom_anim);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}