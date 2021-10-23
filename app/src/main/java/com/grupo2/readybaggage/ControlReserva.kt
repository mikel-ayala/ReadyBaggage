package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.lang.Exception

class ControlReserva {

    companion object {

        private var reservaList: MutableList<Reserva> = mutableListOf()

        fun addReserva(context: Context, idCliente: String, idProducto: String, cantidadProducto: String, fSolicitud: String, origen: String, destino: String, fRecogida: String, fEntrega: String, hRecogida: String, hEntrega: String): Boolean {

            try {

                val db = SQLHandler(context, "bdReadyBaggage1.db", null, 1)
                val add = db.writableDatabase

                var reserva = ContentValues()
                reserva.put("idCliente", idCliente)
                reserva.put("idProducto", idProducto)
                reserva.put("cantidadProducto", cantidadProducto)
                reserva.put("origen", origen)
                reserva.put("destino", destino)
                reserva.put("fRecogida", fRecogida)
                reserva.put("fEntrega", fEntrega)
                reserva.put("hRecogida", hRecogida)
                reserva.put("hEntrega", hEntrega)
                reserva.put("ubicacion", "almacen")

                add.insert("reserva", null, reserva)

                return true

            }//try
            catch (e: Exception){

                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")

            }//catch

            return false

        }//addReserva

        fun getReservas(context: Context, idCliente: String): MutableList<Reserva>? {

            try{

                val db = SQLHandler(context, "bdReadyBaggage1.db", null, 1)
                val get = db.readableDatabase

                var lista = get.rawQuery("SELECT * FROM reserva WHERE idCliente=?", arrayOf(idCliente))

                var reserva: Reserva

                while (lista.moveToNext()){

                    reserva =  Reserva(lista.getString(0), lista.getString(1),
                        lista.getString(2), lista.getString(3),
                        lista.getString(4), lista.getString(5),
                        lista.getString(6), lista.getString(7),
                        lista.getString(8), lista.getString(9),
                        lista.getString(10), lista.getString(11))

                    reservaList.add(reserva)

                }//Mientras hay siguiente

                return reservaList

            }//try
            catch (e: Exception){

                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")

            }//catch

            return null

        }//getReservas

    }//companion object

}//ControlReserva