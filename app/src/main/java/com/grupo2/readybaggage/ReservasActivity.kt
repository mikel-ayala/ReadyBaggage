package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.ControlCliente.Companion.getClienteObject
import com.grupo2.readybaggage.ControlReserva.Companion.getReservas

class ReservasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        var cliente = getClienteObject()

        if (cliente != null) {

            var reservaList: MutableList<Reserva>? = getReservas(this, cliente.idCliente)

            if (reservaList != null){

                for (reserva in reservaList){
                    print(reserva)
                }

            }

        }//not null

    }//onCreate

}//ReservasActivity