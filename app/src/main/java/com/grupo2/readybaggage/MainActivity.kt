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

    private lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private var currentLang: String? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        val idiomas=getSharedPreferences("idiomas", 0)
        currentLang = if(idiomas.getString("idioma", "").isNullOrEmpty()){
            currentLanguage.toString()
        }else{
            idiomas.getString("idioma", "")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btIdiomas.setOnClickListener{
            showPopup(btIdiomas)
        }

        iconoPerfil.setOnClickListener {
            //val profileIntent = Intent(this, ActivityPorfileTemp::class.java)
            //startActivity(profileIntent)

            if (ControlCliente.getCliente() != null) {
                val profileIntent = Intent(this, ActivityPorfileTemp::class.java)
                startActivity(profileIntent)
            } else {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
            }



        }
    }


    private fun showPopup(v : View){
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
            finish()
            startActivity(refresh)
    }
}