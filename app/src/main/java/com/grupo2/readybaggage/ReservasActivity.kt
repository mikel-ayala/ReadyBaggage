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
import kotlinx.coroutines.*

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
        /*
            Emplearemos la corruina sobre el siguiente Dispatcher ya predefinido y destinado
            a consultas a bases de datos y/o servidores.
            Dado que conforme se reciba el listado de la consulta, se iniciara o no el RecyclerView
            Es por ello que en este caso no necesitamos declarar la corrutina dentro de una variable
            que retorne una respuesta
         */
        GlobalScope.launch(Dispatchers.IO) {
            reservasList = ControlReserva.getAllReservas(contextActivity, !isAdmin)
            if (reservasList != null) {
                reservasList!!.sortByDescending{it.idReserva}
                initRecycler(reservasList!!)
            }else{
                Toast.makeText(contextActivity, R.string.no_reservas, Toast.LENGTH_LONG).show()
            }
        }

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

    }

}