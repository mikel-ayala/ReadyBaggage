package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_idioma, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()
        if (iditem==R.id.spanish){

        }
        if (iditem==R.id.euskera){

        }
        return true
    }
}