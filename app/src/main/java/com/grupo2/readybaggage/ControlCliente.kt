package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context

class ControlCliente() {
    companion object {
        fun aniadirCliente(contextInstance: Context) {
            //fun addCliente(email: String, pass: String, nombre: String) {
            // USAR TRY CATCH EN ESTOS METODOS
            println("***Trying to add new CLIENT")
            val admin = SQLHandler(contextInstance," bdReadyBaggage.db", null, 1)
            val bd = admin.writableDatabase
            val row = ContentValues()
            row.put("email", "luis@gmail.com")
            row.put("pass", "123456aa")
            row.put("nombre", "Luis")
            bd.insert("empleado", null, row)
            bd.close()
            println("***Client added successfully")
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