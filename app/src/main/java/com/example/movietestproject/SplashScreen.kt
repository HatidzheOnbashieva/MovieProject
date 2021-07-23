package com.example.movietestproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable() {
            run() {
                val homeActivity = Intent(this, MainActivity::class.java)
                startActivity(homeActivity)

            }
        },1500);

    }
}