package com.grupo2.readybaggage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import java.lang.Exception

class Preferences {

    companion object{

        fun setUserPreferences(activity: Activity, prefName: String, dataName: String, dataValue: String): Boolean {
            try {
                var sharedPref = activity.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                var editor = sharedPref.edit()
                editor.putString(dataName, dataValue)
                editor.apply()
                return true;
            } catch (e: Exception) {
                //Pass this catch
            }
            return false
        }

        fun getUserPreferences(activity: Activity, prefName: String, dataName: String): String? {
            try {
                var sharedPref = activity.getSharedPreferences(prefName, Context.MODE_PRIVATE)
                return sharedPref.getString(dataName, null)
            }catch (e: Exception) {
                //Pass this catch
            }
            return null
        }
        /*
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
         */
    }
}