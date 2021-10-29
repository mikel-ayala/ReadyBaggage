package com.grupo2.readybaggage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.activity_reservas.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*

class ReservasActivity : AppCompatActivity() {

    var reservasList: MutableList<Reserva>? = mutableListOf()
    var contextActivity: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        //Menu idiomas
        btIdiomas.setOnClickListener {
            Utils.showPopup(btIdiomas, this, this)

        }//onClick



        var isAdmin: Boolean = ControlCliente.isAdmin()
        reservasList = ControlReserva.getAllReservas(contextActivity, !isAdmin)

        if (reservasList != null) {

            reservasList!!.sortByDescending{it.idReserva}
            initRecycler(reservasList!!)
<<<<<<< HEAD
        }else{
            Toast.makeText(contextActivity, "${getResources().getString(R.string.no_reservas)}", Toast.LENGTH_LONG).show()
        }
=======

        }//not null
        else{

            Toast.makeText(contextActivity, R.string.no_reservas, Toast.LENGTH_LONG).show()

        }//null
>>>>>>> 449a4eaf5de1795b405e1d1f1aa5ca25da555e6f

        //Ir al perfil
        iconoPerfil.setOnClickListener {
            if (ControlCliente.getClienteName() != null) {
                startActivity<ProfileActivity>()

            }//logged
            else {
                startActivity<LoginActivity>()

            }//not logged

        }//onClick

        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
        }//onClick

        iconoContacto.setOnClickListener {
            startActivity<ContactoActivity>()
        }//onClick

    }//onCreate

    fun initRecycler(ReservaList: MutableList<Reserva>) {

        reservasViewRVMain.layoutManager = LinearLayoutManager(this)
        val adapter = ReservaAdapter(this,ReservaList)
        reservasViewRVMain.adapter = adapter

    }//initRecycler

}//ReservasActivity