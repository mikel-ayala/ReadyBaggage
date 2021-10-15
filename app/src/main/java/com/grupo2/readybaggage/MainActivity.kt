package com.grupo2.readybaggage

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String


    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        currentLang=currentLanguage.toString()
//        if(SharedApp.prefs.dato.isNullOrBlank()){
//            currentLang= currentLanguage.toString()
//
//        }else{
//            currentLang=SharedApp.prefs.dato
//        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btIdiomas.setOnClickListener{
            showPopup(btIdiomas)
        }

        //Para reservar maletas de menos de 10kg
        reservaMenos10.setOnClickListener() {
            //Comprueba si el usuario está logeado
            if (ControlCliente.getCliente() != null) {
                val bookingView = Intent(this, BookingActivity::class.java)
                bookingView.putExtra("productoId","1")
                startActivity(bookingView)

            } else { //Si no está logeado, te manda al login
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                Toast.makeText(this, "Se requiere iniciar sesion para reservar", Toast.LENGTH_LONG).show()
            }
        }

        //Para reservar maletas de más de 10kg
        reservaMas10.setOnClickListener() {
            //Comprueba si el usuario está logeado
            if (ControlCliente.getCliente() != null) {
                val bookingView = Intent(this, BookingActivity::class.java)
                bookingView.putExtra("productoId","2")
                startActivity(bookingView)

            } else { //Si no está logeado, te manda al login
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                Toast.makeText(this, "Se requiere iniciar sesion para reservar", Toast.LENGTH_LONG).show()
            }
        }

        //Para reservar maletas de más de 20kg
        reservaMas20.setOnClickListener() {
            //Comprueba si el usuario está logeado
            if (ControlCliente.getCliente() != null) {
                val bookingView = Intent(this, BookingActivity::class.java)
                bookingView.putExtra("productoId","3")
                startActivity(bookingView)

            } else { //Si no está logeado, te manda al login
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                Toast.makeText(this, "Se requiere iniciar sesion para reservar", Toast.LENGTH_LONG).show()
            }
        }

        iconoPerfil.setOnClickListener {
            //Comprueba si el usuario está logeado
            if (ControlCliente.getCliente() != null) {
                val profileIntent = Intent(this, ProfileActivity::class.java)
                startActivity(profileIntent)
            } else { //Si no está logeado, te manda al login
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
            }



        }
    }

    //Desplegar menú
    private fun showPopup(v : View){
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_idioma, popup.menu)
        //Controlar click en los items
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.spanish-> {
//                    SharedApp.prefs.dato="es"
                    setLocale("es")
                }
                R.id.euskera-> {
//                    SharedApp.prefs.dato="eu"
                    setLocale("eu")
                }
                R.id.english-> {
//                    SharedApp.prefs.dato="en"
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
                this::class.java
            )
            refresh.putExtra(currentLang, localeName)
            finish()
            startActivity(refresh)
    }

    //Comprobar usuario
//    fun isSavedName():Boolean{
//        val myName = SharedApp.prefs.name
//        return myName != ""
//    }
}