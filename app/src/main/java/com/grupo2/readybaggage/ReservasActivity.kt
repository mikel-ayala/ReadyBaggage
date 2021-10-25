package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_reservas.*

class ReservasActivity : AppCompatActivity() {

    var reservasList: MutableList<Reserva>? = mutableListOf()



    //val isAdmin: Int? = extras?.getInt("isAdmin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)
        var isAdmin: Boolean = intent.getBooleanExtra("isAdmin",false)
        println("*****Estado: "+isAdmin.toString())
       // if (isAdmin != null) {
            reservasList = ControlReserva.getAllReservas(this, !isAdmin)
       // } else {
       //     reservasList = ControlReserva.getAllReservas(this, true)
       // }
        if (reservasList != null) {
            reservasList!!.sortByDescending{it.idReserva}
            initRecycler(reservasList!!)
        }
    }

    fun initRecycler(ReservaList: MutableList<Reserva>) {
        reservasViewRVMain.layoutManager = LinearLayoutManager(this)
        val adapter = ReservaAdapter(this,ReservaList)
        reservasViewRVMain.adapter = adapter
    }

}