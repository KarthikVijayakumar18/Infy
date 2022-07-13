package com.example.infyproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.infyproject.R;
import com.example.infyproject.databinding.ActivitySplashBinding;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();
    }

    private void setData(){
        int SPLASH_TIME_OUT = 3000;

        //Animation Calls
        Animation topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        //Setting Animations to the elements of Splash
        binding.firstLine.setAnimation(topAnimation);
        binding.secondLine.setAnimation(topAnimation);
        binding.thirdLine.setAnimation(topAnimation);
        binding.fourthLine.setAnimation(topAnimation);
        binding.fifthLine.setAnimation(topAnimation);
        binding.sixthLine.setAnimation(topAnimation);
        binding.logo.setAnimation(middleAnimation);

        //Splash Screen Code to call new Activity after some time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}