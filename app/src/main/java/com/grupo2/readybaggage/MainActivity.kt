package com.grupo2.readybaggage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grupo2.readybaggage.ControlCliente.Companion.dameCliente
import com.grupo2.readybaggage.ControlCliente.Companion.getCliente
import com.grupo2.readybaggage.Preferences.Companion.cargarCliente
import com.grupo2.readybaggage.Preferences.Companion.guardarCliente
import com.grupo2.readybaggage.Utils.Companion.showPopup
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String


    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        currentLang = currentLanguage.toString()


        dameCliente(this, cargarCliente(this)!!)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Salir de la app
        iconoMain.setOnClickListener {
            guardarCliente(this, getCliente())
            finishAffinity()
        }///onClick

        //Menu idiomas
        btIdiomas.setOnClickListener {
            showPopup(btIdiomas, this, this)
        }//onClick



        //Tarifa -= 10kg
        reservaMenos10.setOnClickListener() {
            reservar(1, 8, getString(R.string.tamanoPeq))
        }//onClick

        //Tarifa + 10kg
        reservaMas10.setOnClickListener() {
            reservar(2,10, getString(R.string.tamanoGran))
        }//onclick

        //Tarifa + 20kg
        reservaMas20.setOnClickListener() {
            reservar(3, 12, getString(R.string.tamanoExtra))
        }//onClick



        //Ir al perfil
        iconoPerfil.setOnClickListener {
            if (ControlCliente.getCliente() != null) {

                startActivity<ProfileActivity>()

            }//logged
            else {

                startActivity<LoginActivity>()

            }//not logged
        }//onClick

        //Ir a reservas
        iconoReservas.setOnClickListener{
            Toast.makeText(this, "Ver las reservas todavia no esta disponible", Toast.LENGTH_LONG).show()
        }//onClick

    }//onCreate

    private fun reservar(id: Int, precio: Int, nombre: String) {

        //Una tarifa ha sido seleccionada
        if (ControlCliente.getCliente() != null) {

            val extras = Bundle()
            val bookingView = Intent(this, BookingActivity::class.java)
            extras.putInt("productoId", id)
            extras.putInt("productoPrecio", precio)
            extras.putString("productoNombre", nombre)
            bookingView.putExtras(extras)
            startActivity(bookingView)

        }//logged
        else {

            startActivity<LoginActivity>()
            Toast.makeText(this, "Se requiere iniciar sesion para reservar", Toast.LENGTH_LONG).show()

        }//not logged

    }//reservar

}//MainActivity