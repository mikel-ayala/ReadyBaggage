package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo2.readybaggage.Preferences.Companion.getUserPreferences
import com.grupo2.readybaggage.Utils.Companion.startActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userLang: String? = getUserPreferences(this,"userLang","language")
        if (userLang != null) {
            Utils.setLocale(userLang, this, this, true)
        }
        startActivity<MainActivity>()
    }
}