package com.example.hijab;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SystemClock.sleep(1000);
        Intent home_intent = new Intent(SplashActivity.this,activity_sigin.class);
        startActivity(home_intent);
        finish();
    }
}
