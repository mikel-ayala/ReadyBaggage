package com.grupo2.readybaggage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private var tCliente: String = "create table cliente(idCliente real PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre texT not null, apellidos text, telefono text not null, f_registro text DEFAULT CURRENT_DATE, is_empleado real DEFAULT 0)"
private var tProducto: String = "create table producto(idProducto real PRIMARY KEY AUTOINCREMENT, nombre text not null, desc text not null, precio real not null)"
private var tReserva: String = "create table reserva(idReserva real PRIMARY KEY AUTOINCREMENT, idCliente real not null, idProducto real not null, cantidadProducto real not null, fSolicitud text not null, origen text not null, destino text not null, fRecogida text not null, fEntrega text not null, hRecogida text not null, hEntrega text not null, ubicacion text not null, FOREIGN KEY(idCliente) REFERENCES cliente(idCliente), FOREIGN KEY(idProducto) REFERENCES producto(idProducto))"

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        println("***DB CREATED SUCCESSFULLY")
        db.execSQL(tCliente)
        //db.execSQL(tProducto)
        db.execSQL(tReserva)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //DO SOMETHING HERE TO DEAL WITH DB's CHANGES
        println("***DB HAS BEEN UPDATED")
        db.execSQL("drop table if exists cliente")
        onCreate(db)
    }

    class SQLHandler(
        context: Context, name:
        String, factory: SQLiteDatabase.CursorFactory?, version: Int
    ) :
        SQLiteOpenHelper(context, name, factory, version) {
        var tipoUsuario: String = "";
        var nombreUsuario: String = "";

        fun requestToLogIn(vUser: String, vPass: String): Boolean {
            if (tipoUsuario.isNotEmpty() || nombreUsuario.isNotEmpty()) {

            }
            return false;
        }

        fun requestUserType(): String? {
            if (tipoUsuario.isNotEmpty()) {
                return tipoUsuario;
            }
            println("No hay usuario logeado")
            return null;
        }

        fun setDBConnection() {

        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("create table usuario(idUser int primary key, user text, pass text)")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("drop table if exists articulos")
            onCreate(db)
        }

    }
}