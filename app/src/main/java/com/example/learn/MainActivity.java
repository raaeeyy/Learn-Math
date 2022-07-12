package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    private static int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(1800);
        logo = (ImageView) findViewById(R.id.splashLogo);

        logo.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}