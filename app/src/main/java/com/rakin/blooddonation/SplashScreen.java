package com.rakin.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar pro_bar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        pro_bar = findViewById(R.id.progressBarID);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                runProgressBar();
                startMainactivity();

            }
        });

        thread.start();
    }

    private void startMainactivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }

    private void runProgressBar() {

        for(progress = 25; progress<=100; progress=progress+25){
            try {
                Thread.sleep(800);
                pro_bar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
