package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ControlCliente() {
    companion object {
        private var userLogged: String? = null
        private var userObject: Cliente? = null
        //Función para registrar clientes
        fun registrarCliente(contextInstance: Context, pUsername: String, pPassword: String, pNombre: String, pApellidos: String?, pTelefono: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("email", pUsername)
                row.put("pass", pPassword)
                row.put("nombre", pNombre)
                if (pApellidos != null){
                    row.put("apellidos", pApellidos)
                }
                row.put("telefono", pTelefono)
                bd.insert("cliente", null, row)
                bd.close()
                return true
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
        }

        //Función para comprobar si el cliente ya existe
        fun existeCliente(contextInstance: Context, pEmail: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT email FROM cliente WHERE email=?", arrayOf(pEmail))
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

        }


        fun logCliente(contextInstance: Context, pUser: String, pPass: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT idCliente, email, pass, nombre, apellidos, telefono, f_registro, is_empleado FROM cliente WHERE email=? and pass=?", arrayOf(pUser,pPass))
                if (fila.moveToFirst()) {

                    this.userLogged = fila.getString(1)
                    this.userObject = Cliente(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5),fila.getString(6), fila.getString(7))
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

        fun dameCliente(contextInstance: Context, email: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT idCliente, email, pass, nombre, apellidos, telefono, f_registro, is_empleado FROM cliente WHERE email=?", arrayOf(email))
                if (fila.moveToFirst()) {

                    this.userLogged = fila.getString(1)
                    this.userObject = Cliente(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5),fila.getString(6), fila.getString(7))
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

        fun clienteIsLogged(): Boolean {
            if (this.userLogged != null) {
                return true
            }
            return false
        }

        //Función para hacer UPDATE de los datos de cliente
        fun modificarCliente(contextInstance: Context, pCliente: Cliente): Boolean {
            if (pCliente == null) {
                return false
            }
            try {
                val admin = SQLHandler(contextInstance, " bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("nombre", pCliente.nombre)
                row.put("apellidos", pCliente.apellidos)
                row.put("email", pCliente.email)
                row.put("pass", pCliente.password)

                //row.put("fec_nac", f)
                row.put("telefono", pCliente.telefono)
                val cant = bd.update("cliente", row, "idCliente=${pCliente.idCliente}", null)
                bd.close()
                if (cant == 1) {
                    println("UPDATE realizada correctamente")
                    return true
                }
                println("UPDATE ERROR")
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
        }

        fun getCliente(): String? {
            return this.userLogged
        }

        fun getClienteObject(): Cliente? {
            return this.userObject
        }

        fun logout(): Boolean {
            if (userLogged != null) {
                this.userLogged = null
                this.userObject = null
                return true
            }
            return false
        }

    }
}