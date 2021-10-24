package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detallesreserva.*
import kotlinx.android.synthetic.main.activity_reservas.*

class DetallesReservaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallesreserva)

        val gson = Gson()
        val reserva = gson.fromJson<Reserva>(intent.getStringExtra("reserva"), Reserva::class.java)

        dRvTxtId.text = "ID: "+String.format("%06d", reserva.idReserva.toInt())
        dRvTxtFsolicitud.text = reserva.f_solicitud
    }
}