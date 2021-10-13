package com.grupo2.readybaggage

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_NAME = "datos"
    val SHARED_NAME = "idioma"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var dato: String
        get() = prefs.getString(SHARED_NAME, "").toString()
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}