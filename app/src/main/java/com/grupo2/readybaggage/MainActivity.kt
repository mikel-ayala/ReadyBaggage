package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var locale: Locale
    private var currentLang: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //insertar menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_idioma, menu)
        return true
    }
    //items de menú clickables
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.spanish -> setLocale("es")
            R.id.euskera -> setLocale("eu")
            R.id.english -> setLocale("en")
        }
        return true
    }

    //cambiar idioma
    private fun setLocale(localeName: String) {
        locale = Locale(localeName)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(
            this,
            this::class.java
        )
        refresh.putExtra(currentLang, localeName)
        startActivity(refresh)
    }
}