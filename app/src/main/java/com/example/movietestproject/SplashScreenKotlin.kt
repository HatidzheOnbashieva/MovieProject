package com.example.movietestproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_kotlin)

            Handler().postDelayed(Runnable() {
                run() {
                    val homeActivity = Intent(this, MainActivity2Kotlin::class.java)
                    startActivity(homeActivity)

                }
            },1500);
    }
}