package com.grupo2.readybaggage

import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class Utils {
    companion object {
        //Función para comprobar que el formato del nº de teléfono es correcto
        fun validarTelefono(pTelefono: String): Boolean {
            try {
                var esNumero: Int? = null
                esNumero = Integer.parseInt(pTelefono)
                if (esNumero.toString().substring(0,1).equals("6") && esNumero.toString().length == 9) {
                    return true
                }
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: ${functionName.toString()}")
            }
            return false
        }

        //Función para comprobar que el formato del email es correcto
        fun validarEmail(pEmail: String): Boolean {
            return !TextUtils.isEmpty(pEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(pEmail).matches()
        }

        //Función para comprobar que el formato de la contraseña es correcto
        fun validarPassword(pPassword: String): Boolean  {
            var passTemp = pPassword.replace(" ","")
            if (passTemp.length >= 6) {
                return true
            }
            return false
        }
    }
}