package com.example.news.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.example.news.R;

import static java.lang.Thread.sleep;
import static java.security.AccessController.getContext;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.setMaxFrame(50);
        LottieAnimationView animationView2 = findViewById(R.id.textView);
        int yourColor = ContextCompat.getColor(this, R.color.primaryTextColor);

        SimpleColorFilter filter = new SimpleColorFilter(yourColor);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        animationView.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
        animationView2.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);

        animationView.setPadding(-120, -120, -120, -120);
        animationView2.setPadding(-220, -220, -220, -220);

            splashScreen(SPLASH_SCREEN_TIME);
        }

        private void splashScreen(int timeSplashScreen) {
            final Thread thread = new Thread(() -> {
                try {
                    sleep(timeSplashScreen);
                    startActivity(new Intent(SplashScreen.this, SelectionActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
}