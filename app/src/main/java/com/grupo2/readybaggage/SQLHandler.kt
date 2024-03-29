package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private var tCliente: String = "create table cliente(idCliente INTEGER PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text NOT NULL, nombre text NOT NULL, apellidos text, telefono text NOT NULL, f_registro text DEFAULT CURRENT_DATE, is_empleado INTEGER DEFAULT 0)"
private var tProducto: String = "create table producto(idProducto real PRIMARY KEY AUTOINCREMENT, nombre text not null, desc text not null, precio real not null)"
private var tReserva: String = "create table reserva(idReserva INTEGER PRIMARY KEY AUTOINCREMENT, idCliente INTEGER not null, idProducto INTEGER not null, cantidad INTEGER not null, f_solicitud text not null, origen text not null, destino text not null, fRecogida text not null, fEntrega text not null, hRecogida text not null, hEntrega text not null, metodoPago text not null, estado text not null default 'estado0', FOREIGN KEY(idCliente) REFERENCES cliente(idCliente))"

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        println("[DEBUG] Data Base successfully created")
        db.execSQL(tCliente)
        db.execSQL(tReserva)

        val admin = ContentValues()
        admin.put("email", "admin@gmail.com")
        admin.put("pass", "Admin_1234")
        admin.put("nombre", "Administrador")
        admin.put("apellidos", "Oficial")
        admin.put("telefono", "699999999")
        admin.put("is_empleado", 1)
        db.insert("cliente", null, admin)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //DO SOMETHING HERE TO DEAL WITH DB's CHANGES
        println("[DEBUG] Data Base Updated | Check it out")
        db.execSQL("drop table if exists cliente")
        onCreate(db)
    }
}