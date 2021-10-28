package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*

class ContactoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        //Menu idiomas
        btIdiomas.setOnClickListener {
            Utils.showPopup(btIdiomas, this, this)
        }//onClick



        //Ir al perfil
        iconoPerfil.setOnClickListener {
            startActivity<ProfileActivity>()

        }//onClick

        //Ir al main
        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
        }//onClick

        //Ir a las reservas
        iconoReservas.setOnClickListener{
            startActivity<ReservasActivity>()
        }//onClick
    }
}