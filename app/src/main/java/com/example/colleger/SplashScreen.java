package com.example.colleger;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app's main activity
                Intent i = new Intent(SplashScreen.this, StartActivity.class);
                startActivity(i);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}