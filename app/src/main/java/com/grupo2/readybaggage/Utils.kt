package com.grupo2.readybaggage

<<<<<<< Updated upstream
import android.text.TextUtils

=======
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import java.lang.Exception
import java.util.*
>>>>>>> Stashed changes

class Utils {
    companion object {


        //Función para comprobar que el formato del nº de teléfono es correcto
        fun validarTelefono(pTelefono: String): Boolean {
            try {
                val esNumero: Int?
                esNumero = Integer.parseInt(pTelefono)
                if (esNumero.toString().substring(0,1) == "6" && esNumero.toString().length == 9) {
                    return true
                }
                return false
            } catch (e: Exception) {
                val functionName = object{}.javaClass.enclosingMethod.name
                println("CATCHING ERROR AT: $functionName")
            }
            return false
        }

        //Función para comprobar que el formato del email es correcto
        fun validarEmail(pEmail: String): Boolean {
            return !TextUtils.isEmpty(pEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(pEmail).matches()
        }

        //Función para comprobar que el formato de la contraseña es correcto
        fun validarPassword(pPassword: String): Boolean  {
            val passTemp = pPassword.replace(" ","")
            if (passTemp.length >= 6) {
                return true
            }
            return false
        }

<<<<<<< Updated upstream

=======
        //Desplegar menú
        fun showPopup(v : View, activity: Activity, context: Context){
            val popup = PopupMenu(activity, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_idioma, popup.menu)
            //Controlar click en los items
            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.spanish-> {
//                    SharedApp.prefs.dato="es"
                        setLocale("es", activity, context)
                    }
                    R.id.euskera-> {
//                    SharedApp.prefs.dato="eu"
                        setLocale("eu", activity, context)
                    }
                    R.id.english-> {
//                    SharedApp.prefs.dato="en"
                        setLocale("en", activity, context)
                    }
                }
                true
            }
            popup.show()
        }

        //cambiar idioma
        private fun setLocale(localeName: String, activity: Activity, context: Context) {
            locale = Locale(localeName)
            val res = activity.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent (
                context,
                activity::class.java
            )
            currentLang=currentLanguage.toString()
            refresh.putExtra(currentLang, locale)
            activity.finish()
            context.startActivity(refresh)
        }
>>>>>>> Stashed changes
    }
}
