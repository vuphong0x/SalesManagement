package com.teamone.salesmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private Animation topAnim, bottomAnim;
    private ImageView image;
    private TextView tvLogo, tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        tvLogo = findViewById(R.id.tvLogo);
        tvSlogan = findViewById(R.id.tvSlogan);

        image.setAnimation(topAnim);
        tvLogo.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}