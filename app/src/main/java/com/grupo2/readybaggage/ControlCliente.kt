package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import java.lang.Exception

class ControlCliente() {
    companion object {
        fun aniadirCliente(contextInstance: Context, pUsername: String, pPassword: String): Boolean {
            //fun addCliente(email: String, pass: String, nombre: String) {
            // USAR TRY CATCH EN ESTOS METODOS
            //println("***Trying to add new CLIENT")
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("email", pUsername)
                row.put("pass", pPassword)
                row.put("nombre", "luis")
                row.put("apellidos", "villarroel")
                row.put("fec_nac", "29/01/1998")
                row.put("telefono", "666666666")
                bd.insert("cliente", null, row)
                bd.close()
                return true
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
            //println("***Client added successfully")
        }

        fun existeCliente(contextInstance: Context, pEmail: String): Boolean {
            try {
                println("****EmailParam: ${pEmail}")
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT email FROM cliente" + " WHERE email=?", arrayOf(pEmail))
                if (fila.moveToFirst()) {
                    bd.close()
                    return true
                }
                bd.close()
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
                return false
            }
            return false
        }

        fun modificarCliente(contextInstance: Context, pCliente: Cliente): Boolean {
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT email FROM cliente" + " WHERE email=?", null)
                if (fila.moveToFirst()) {
                    bd.close()
                    return true
                }
                bd.close()
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
        }

    }
    /*
    fun aniadirCliente(contextInstance: Context) {
    //fun addCliente(email: String, pass: String, nombre: String) {

        val admin = SQLHandler(contextInstance," bdReadyBaggage.db", null, 1)
        val bd = admin.writableDatabase
        val row = ContentValues()
        row.put("email", "luis@gmail.com")
        row.put("pass", "123456aa")
        row.put("nombre", "Luis")
        bd.insert("empleado", null, row)
        bd.close()
    }



    fun buscarCliente() {

    }

     */
}