package com.mydelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mydelivery.activity.WelcomeActivitiy;
import com.mydelivery.drawer.DrawerActivity;

import java.io.IOException;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class SplashActivity extends AppCompatActivity {
    private Intent intent;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(SplashActivity.this);

        exitSplash();

    }
    public void exitSplash() {
        final int SPLASH_TIME_OUT = 1600;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("run", "run: ");
                    intent = new Intent(SplashActivity.this, WelcomeActivitiy.class);
                    startActivity(intent);
                    finish();

            }
        };
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }



}
