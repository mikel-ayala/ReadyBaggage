package com.grupo2.readybaggage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.activity_contacto.*
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

        //Ir al main
        iconoMain.setOnClickListener {
            startActivity<MainActivity>()

        }//onClick


        //Icono Facebook
        iconoFB.setOnClickListener{
            abrirweb("https://www.facebook.com/")

        }//onClick

        //Icono Twitter
        iconoTwitter.setOnClickListener{
            abrirweb("https://twitter.com")

        }//onClick

        //Icono Instagram
        iconoInsta.setOnClickListener{
            abrirweb("https://www.instagram.com/")

        }//onclick

        //Icono LinkedIn
        iconoLinkedIn.setOnClickListener{
            abrirweb("https://www.linkedin.com/")

        }//onClick

        //Icono Whatsapp
        iconoWhatsapp.setOnClickListener{
            abrirweb("https://wa.me/521123456955")

        }//onClick

        //Ir al perfil
        iconoPerfil.setOnClickListener {
            startActivity<ProfileActivity>()

        }//onClick

        //Ir a las reservas
        iconoReservas.setOnClickListener{
            startActivity<ReservasActivity>()

        }//onClick

    }//onCreate

    //Ir al navegador
    fun abrirweb(web:String){

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
        startActivity(browserIntent)

    }//abrirweb

}//ContactoActivity