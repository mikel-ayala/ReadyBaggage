package com.grupo2.readybaggage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        println("[DEBUG] Data Base successfully created")

        db.execSQL("create table cliente(idCliente INTEGER PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre texT not null, apellidos text, telefono text not null, f_registro text DEFAULT CURRENT_DATE, is_empleado real DEFAULT 0)")
        //db.execSQL("create table producto(idProducto real PRIMARY KEY AUTOINCREMENT, nombre text not null, descripcion text not null, precio real not null)")
        db.execSQL("create table reserva(idReserva INTEGER PRIMARY KEY AUTOINCREMENT, idCliente real not null, idProducto real not null, cantidadProducto real not null, fSolicitud text DEFAULT CURRENT_DATE, origen text not null, destino text not null, fRecogida text not null, fEntrega text not null, hRecogida text not null, hEntrega text not null, ubicacion text not null, FOREIGN KEY(idCliente) REFERENCES cliente(idCliente))") //, FOREIGN KEY(idProducto) REFERENCES producto(idProducto)

        val admin = ContentValues()
        admin.put("email", "admin@gmail.com")
        admin.put("pass", "Admin_1234")
        admin.put("nombre", "Administrador")
        admin.put("apellidos", "Oficial")
        admin.put("telefono", "999999999")
        db.insert("cliente", null, admin)

        var reserva1 = ContentValues()
        reserva1.put("idCliente", 1)
        reserva1.put("idProducto", 1)
        reserva1.put("cantidadProducto", 1)
        reserva1.put("origen", "bilbo")
        reserva1.put("destino", "respal")
        reserva1.put("fRecogida", "10/22/2021")
        reserva1.put("fEntrega", "11/22/2021")
        reserva1.put("hRecogida", "1:51")
        reserva1.put("hEntrega", "2:52")
        reserva1.put("ubicacion", "almacen")

        db.insert("reserva", null, reserva1)

        var reserva = ContentValues()
        reserva.put("idCliente", 1)
        reserva.put("idProducto", 2)
        reserva.put("cantidadProducto", 3)
        reserva.put("origen", "basa")
        reserva.put("destino", "galda")
        reserva.put("fRecogida", "12/25/2021")
        reserva.put("fEntrega", "12/22/2021")
        reserva.put("hRecogida", "3:53")
        reserva.put("hEntrega", "5:53")
        reserva.put("ubicacion", "almacen")

        db.insert("reserva", null, reserva)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //DO SOMETHING HERE TO DEAL WITH DB's CHANGES
        println("[DEBUG] Data Base Updated | Check it out")
        db.execSQL("drop table if exists cliente")
        onCreate(db)
    }
}