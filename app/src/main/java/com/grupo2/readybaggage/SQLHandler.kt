package com.grupo2.readybaggage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {

        println("[DEBUG] Data Base successfully created")
        db.execSQL("create table cliente(idCliente real PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre texT not null, apellidos text, telefono text not null, f_registro text DEFAULT CURRENT_DATE, is_empleado real DEFAULT 0)")
        //db.execSQL("create table producto(idProducto real PRIMARY KEY AUTOINCREMENT, nombre text not null, descripcion text not null, precio real not null)")
        //db.execSQL("create table reserva(idReserva real PRIMARY KEY AUTOINCREMENT, idCliente real not null, idProducto real not null, cantidadProducto real not null, fSolicitud text not null, origen text not null, destino text not null, fRecogida text not null, fEntrega text not null, hRecogida text not null, hEntrega text not null, ubicacion text not null, FOREIGN KEY(idCliente) REFERENCES cliente(idCliente), FOREIGN KEY(idProducto) REFERENCES producto(idProducto))")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //DO SOMETHING HERE TO DEAL WITH DB's CHANGES
        println("[DEBUG] Data Base Updated | Check it out")
        db.execSQL("drop table if exists cliente")
        //db.execSQL("drop table if exists producto")
        //db.execSQL("drop table if exists reserva")
        onCreate(db)
    }

    /*
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
     */
}