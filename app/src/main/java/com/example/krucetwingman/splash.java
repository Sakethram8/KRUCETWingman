package com.example.krucetwingman;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td = new Thread() {
            public void run() {
                try {
                    sleep(4000);

                } catch (Exception ex) {
                    ex.printStackTrace();

                } finally {
                    Intent intent = new Intent(splash.this, loginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
        td.start();
    }
}