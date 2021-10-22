package com.grupo2.readybaggage

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userLang: String? = Preferences.getUserPreferences(this,"userLang","language")
        if (userLang != null) {
            Utils.setLocale(userLang, this, this, true)
        } else {
            Utils.setLocale("es", this, this, true)
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}