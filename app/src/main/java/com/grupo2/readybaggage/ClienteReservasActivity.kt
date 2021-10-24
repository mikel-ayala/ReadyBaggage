package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_reservas.*

class ClienteReservasActivity : AppCompatActivity() {

    var reservasList: MutableList<Reserva>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientereservas)

        reservasList = ControlReserva.getAllReservas(this, true)
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