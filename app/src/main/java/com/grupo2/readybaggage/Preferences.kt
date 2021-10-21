package com.grupo2.readybaggage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Preferences {

    companion object{

        fun guardarCliente(activity: Activity, email: String?){

            var sharedPref = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            var editor = sharedPref.edit()

            editor.putString("cliente", email)
            editor.apply()

        }

        fun cargarCliente(activity: Activity): String? {

            var sharedPref = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE)

            return sharedPref.getString("cliente", "")

        }
    }
}