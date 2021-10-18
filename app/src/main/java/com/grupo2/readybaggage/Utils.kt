package com.grupo2.readybaggage

import android.content.Intent
import android.text.TextUtils
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.*
import java.lang.Exception
import java.util.*

class Utils {
    companion object {

        private lateinit var locale: Locale
        private var currentLanguage = Locale.getDefault()
        private lateinit var currentLang: String

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

        //Desplegar menú
        private fun showPopup(v : View){
            val popup = PopupMenu(this, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_idioma, popup.menu)
            //Controlar click en los items
            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.spanish-> {
//                    SharedApp.prefs.dato="es"
                        setLocale("es")
                    }
                    R.id.euskera-> {
//                    SharedApp.prefs.dato="eu"
                        setLocale("eu")
                    }
                    R.id.english-> {
//                    SharedApp.prefs.dato="en"
                        setLocale("en")
                    }
                }
                true
            }
            popup.show()
        }

        //cambiar idioma
        private fun setLocale(localeName: String) {
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(
                this,
                this::class.java
            )
            refresh.putExtra(currentLang, localeName)
            finish()
            startActivity(refresh)
        }
    }
}