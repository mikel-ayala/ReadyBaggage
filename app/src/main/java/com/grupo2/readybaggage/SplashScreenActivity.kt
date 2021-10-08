package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }
}