package edu.sbu.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class spalashscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenactivity);

        EasySplashScreen config = new EasySplashScreen(spalashscreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3500)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withAfterLogoText("Welcome...! ;)");

        config.getAfterLogoTextView().setTextColor(Color.parseColor("#808080"));

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
