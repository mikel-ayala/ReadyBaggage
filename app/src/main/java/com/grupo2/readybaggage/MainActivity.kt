package com.grupo2.readybaggage

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private var currentLang: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        val idiomas=getSharedPreferences("idiomas", 0)
        if(idiomas.getString("idioma", "").isNullOrEmpty()){
            currentLang = currentLanguage.toString()
        }else{
            currentLang = idiomas.getString("idioma", "")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btIdiomas.setOnClickListener{
            showPopup(btIdiomas)
        }

        btnLogin.setOnClickListener() {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }


    fun showPopup(v : View){
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_idioma, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.spanish-> {
//                    idiomas.edit().putString("idioma", "es")
                    setLocale("es")
                }
                R.id.euskera-> {
//                    idiomas.edit().putString("idioma", "eu")
                    setLocale("eu")
                }
                R.id.english-> {
//                    idiomas.edit().putString("idioma", "en")
                    setLocale("en")
                }
            }
            true
        }
        popup.show()
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
                MainActivity::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
    }
}