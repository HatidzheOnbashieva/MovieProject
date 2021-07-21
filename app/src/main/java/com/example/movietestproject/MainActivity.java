package com.example.movietestproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       SplashScreenFragment splashScreen = new SplashScreenFragment();
       //getSupportFragmentManager().beginTransaction().add(R.id.mainScreen, splashScreen).commit();
    }
}