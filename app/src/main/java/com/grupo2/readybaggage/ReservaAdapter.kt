package com.grupo2.readybaggage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_reserva.view.*

class
ReservaAdapter (var mContext: Context, val reservasList: MutableList<Reserva>): RecyclerView.Adapter<ReservaAdapter.ReservaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReservaHolder(layoutInflater.inflate(R.layout.item_reserva, parent, false))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        holder.render(reservasList[position])
    }


    override fun getItemCount(): Int = reservasList.size


    fun getStringIdentifier(context: Context, name: String?): Int {
        return context.resources.getIdentifier(name, "string", context.packageName)
    }
    inner class ReservaHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(reserva: Reserva) {
//            view.itemReservasId.text = reserva.idReserva
            view.itemReservasId.text = "ID: "+String.format("%06d", reserva.idReserva.toInt())
            view.itemReservasNombre.text = view.context.getString(R.string.nombre) + ": "+reserva.nombreCliente + " " + reserva.apellidoCliente
            view.itemReservasOrigen.text = view.context.getString(R.string.origen) + ": "+reserva.origen
            view.itemReservasDestino.text = view.context.getString(R.string.destino) + ": "+reserva.destino
            view.itemReservasEstado.text = view.context.getString(getStringIdentifier(view.context,reserva.estado))
            when (reserva.estado) {
                "estado0" -> view.itemReservasEstado.setBackgroundResource(R.drawable.rounded_orange)
                "estado1" -> view.itemReservasEstado.setBackgroundResource(R.drawable.rounded_purple)
                "estado2" -> view.itemReservasEstado.setBackgroundResource(R.drawable.rounded_blue)
                "estado3" -> view.itemReservasEstado.setBackgroundResource(R.drawable.rounded_green)
                else -> {
                    view.itemReservasEstado.setBackgroundResource(R.drawable.rounded_orange)
                }
            }

            when (reserva.idProducto) {
                "1" -> view.itemReservasIV.setImageResource(R.drawable.maleta_pequenia)
                "2" -> view.itemReservasIV.setImageResource(R.drawable.maleta_grande)
                "3" -> view.itemReservasIV.setImageResource(R.drawable.maleta_extra)
                else -> {
                    view.itemReservasIV.setImageResource(R.drawable.maleta_pequenia)
                }
            }
            view.setOnClickListener() {
                var gson: Gson = Gson()
                var reservaObj: String = gson.toJson(reserva)
                val extras = Bundle()
                val detallesView = Intent(mContext, DetallesReservaActivity::class.java)
                extras.putString("reserva", reservaObj)
                detallesView.putExtras(extras)
                mContext.startActivity(detallesView)
            }
        }
    }
}