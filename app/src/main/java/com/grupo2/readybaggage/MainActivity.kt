package com.grupo2.readybaggage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grupo2.readybaggage.Utils.Companion.profileActivity
import com.grupo2.readybaggage.Utils.Companion.showPopup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String


    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        currentLang = currentLanguage.toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btIdiomas.setOnClickListener {
            showPopup(btIdiomas, this, this)
        }

        //Para reservar maletas de menos de 10kg
        reservaMenos10.setOnClickListener() {
            reservar(1, 8, getString(R.string.tamanoPeq))
        }

        //Para reservar maletas de más de 10kg
        reservaMas10.setOnClickListener() {
            reservar(2,10, getString(R.string.tamanoGran))
        }

        //Para reservar maletas de más de 20kg
        reservaMas20.setOnClickListener() {
            reservar(3, 12, getString(R.string.tamanoExtra))
        }


        iconoPerfil.setOnClickListener {
            profileActivity(this, this)
        }

        iconoMain.setOnClickListener {
            finish()
        }

        iconoReservas.setOnClickListener{
            Toast.makeText(this, "Ver las reservas todavia no esta disponible", Toast.LENGTH_LONG).show()
        }
    }

    private fun reservar(id: Int, precio: Int, nombre: String) {
        //Comprueba si el usuario está logeado
        if (ControlCliente.getCliente() != null) {
            val extras = Bundle()
            val bookingView = Intent(this, BookingActivity::class.java)
            extras.putInt("productoId", id)
            extras.putInt("productoPrecio", precio)
            extras.putString("productoNombre", nombre)
            bookingView.putExtras(extras)
            startActivity(bookingView)

        } else { //Si no está logeado, te manda al login
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            Toast.makeText(this, "Se requiere iniciar sesion para reservar", Toast.LENGTH_LONG)
                .show()
        }
    }
}