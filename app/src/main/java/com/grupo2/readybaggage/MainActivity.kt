package com.grupo2.readybaggage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        currentLang = currentLanguage.toString()

        var userAccount: String? = Preferences.getUserPreferences(this,"userdata","email")
        var userPassword: String? = Preferences.getUserPreferences(this,"userdata","password")
        if (userAccount != null && userPassword != null) {
            ControlCliente.logCliente(this,userAccount, userPassword)
        }
        //ControlCliente.getCliente(this, cargarCliente(this)!!)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Salir de la app
        iconoMain.setOnClickListener {
            //guardarCliente(this, getCliente())
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
            if (ControlCliente.getClienteName() != null) {
                startActivity<ProfileActivity>()
            }//logged
            else {
                startActivity<LoginActivity>()
            }//not logged
        }//onClick

        //Ir a reservas
        iconoReservas.setOnClickListener{
            startActivity<ReservasActivity>()
        }//onClick

    }//onCreate

    private fun reservar(id: Int, precio: Int, nombre: String) {
        //Una tarifa ha sido seleccionada
        if (ControlCliente.getClienteName() != null) {
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