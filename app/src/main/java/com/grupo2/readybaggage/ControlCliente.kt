package com.grupo2.readybaggage

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import java.lang.Exception

class ControlCliente() {
    companion object {
        private var userLogged: String? = null
        private var userObject: Cliente? = null
        private var userAdmin: Boolean = false

        //Función para registrar clientes
        fun registrarCliente(contextInstance: Context, pUsername: String, pPassword: String, pNombre: String, pApellidos: String?, pTelefono: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("email", pUsername)
                row.put("pass", pPassword)
                row.put("nombre", pNombre)
                //ESTE CASO SIEMPRE SE CUMPLE (REVISAR) UNA CAJA DE TEXTO NUNCA ES NULA DADO QUE ES UNA CADENA DE CARACTERES VACIA
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
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
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
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("SELECT idCliente, email, pass, nombre, apellidos, telefono, f_registro, is_empleado FROM cliente WHERE email=? and pass=?", arrayOf(pUser,pPass))
                if (fila.moveToFirst()) {

                    this.userLogged = fila.getString(1)
                    if (fila.getString(7) == "1") {
                        userAdmin = true
                    }
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

        fun getCliente(contextInstance: Context, email: String): Boolean {
            try {
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
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
        fun updateCliente(contextInstance: Context, pCliente: Cliente): Boolean {
            if (pCliente == null) {
                return false
            }
            try {
                val admin = SQLHandler(contextInstance, "bdReadyBaggage.db", null, 1)
                val bd = admin.writableDatabase
                val row = ContentValues()
                row.put("nombre", pCliente.nombre)
                row.put("apellidos", pCliente.apellidos)
                row.put("email", pCliente.email)
                row.put("pass", pCliente.password)
                row.put("telefono", pCliente.telefono)
                val cant = bd.update("cliente", row, "idCliente=${pCliente.idCliente}", null)
                bd.close()
                if (cant == 1) {
                    println("[DEBUG] Cliente actualizado correctamente")
                    return true
                }
                println("[DEBUG] Error al actualizar cliente")
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("[ERROR] Catched at : ${functionName.toString()}")
            }
            return false
        }

        fun isAdmin(): Boolean = this.userAdmin

        fun getClienteName(): String? {
            return this.userLogged
        }

        fun getClienteObject(): Cliente? {
            return this.userObject
        }

        fun logout(pActivity: Activity): Boolean {
            if (userLogged != null) {
                this.userLogged = null
                this.userObject = null
                this.userAdmin = false
                Preferences.setUserPreferences(pActivity,"userdata","email","")
                Preferences.setUserPreferences(pActivity,"userdata","password","")
                return true
            }
            return false
        }

    }
}