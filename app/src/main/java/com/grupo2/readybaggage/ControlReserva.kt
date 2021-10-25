package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.lang.Exception

class ControlReserva {

    companion object {
        //var reservasList: List<Reserva> = listOf()
        val reservasList: MutableList<Reserva> = mutableListOf()
        fun getAllReservas(contextInstance: Context, userFilter: Boolean): MutableList<Reserva>? {
            try {
                reservasList.clear()
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                var querySyntax: String = "SELECT idReserva, reserva.idCliente, idProducto, cliente.nombre, cliente.apellidos, cantidad, f_solicitud, origen, destino, fRecogida, fEntrega, hRecogida, hEntrega, metodoPago, estado FROM reserva inner join cliente on reserva.idCliente=cliente.idCliente"
                if (userFilter) {
                   var userId: String = ControlCliente.getClienteObject()!!.idCliente
                    querySyntax = "SELECT idReserva, reserva.idCliente, idProducto, cliente.nombre, cliente.apellidos, cantidad, f_solicitud, origen, destino, fRecogida, fEntrega, hRecogida, hEntrega, metodoPago, estado FROM reserva inner join cliente on reserva.idCliente=cliente.idCliente where reserva.idCliente='$userId'"
                }
                println(querySyntax)
                val cursor: Cursor = bd.rawQuery(querySyntax,null)
                if (cursor.moveToFirst()) {
                    do {
                        var vReserva: Reserva = Reserva(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                            cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14))
                        reservasList.add(vReserva)
                    }while (cursor.moveToNext())
                    cursor.close()
                    bd.close()

                    for(element in reservasList){
                        println(element)
                    }
                    return reservasList
                }
                bd.close()
                return null
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("[ERROR] CATCHING ERROR AT: ${functionName.toString()}")
                return null
            }
            return null
        }

        fun registrarReserva(contextInstance: Context, pIdCliente: Int, pIdProducto: Int, pCantidad: Int, vF_solicitud: String, vOrigen: String,
                vDestino: String, vF_recogida: String, vF_entrega: String, vH_recogida: String, vH_entrega: String, vMetodoPago: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("idCliente", pIdCliente)
                row.put("idProducto", pIdProducto)
                row.put("cantidad", pCantidad)
                row.put("f_solicitud", vF_solicitud)
                row.put("origen", vOrigen)
                row.put("destino", vDestino)
                row.put("fRecogida", vF_recogida)
                row.put("fEntrega", vF_entrega)
                row.put("hRecogida", vH_recogida)
                row.put("hEntrega", vH_entrega)
                row.put("metodoPago", vMetodoPago)
                bd.insert("reserva", null, row)
                bd.close()
                return true
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("[ERROR] CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
        }

        fun updateReserva(contextInstance: Context, pReserva: Reserva): Boolean {
            if (pReserva == null) {
                return false
            }
            try {
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("estado", pReserva.estado)
                val cant = bd.update("reserva", row, "idReserva=${pReserva.idReserva}", null)
                bd.close()
                if (cant == 1) {
                    println("[DEBUG] Reserva actualizada correctamente")
                    return true
                }
                bd.close()
                println("[DEBUG] Error al actualizar Reserva")
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("[ERROR] Catched at : ${functionName.toString()}")
            }
            return false
        }



        /*
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
         */

    }//companion object

}//ControlReserva