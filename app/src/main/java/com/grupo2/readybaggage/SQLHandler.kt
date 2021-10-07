package com.grupo2.readybaggage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var tEmpleado: String = "create table empleado(email text primary key, pass text not null, nombre text not null)"

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        println("***DB CREATED SUCCESSFULLY")
        db.execSQL(tEmpleado)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //DO SOMETHING HERE TO DEAL WITH DB's CHANGES
        println("***DB HAS BEEN UPDATED")
    }
}