package com.grupo2.readybaggage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//private var tCliente: String = "create table cliente(codigo int primary key AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre text not null, apellidos text not null, fec_nac text not null, telefono text not null)"
private var tCliente: String = "create table cliente(codigo INTEGER PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre text not null, apellidos text not null, fec_nac text not null, telefono text not null)"
private var tEmpleado: String = "create table empleado(dniEmple text primary key not null unique, email text not null unique, password text not null, nombre text not null, apellidos text not null, fecNac text, direccion text)"
private var tProducto: String = "create table producto(idProducto int primary key autoincrement, nombre text not null, desc text not null, precio real not null)"
private var tOrdenServicio: String = "create table ordenservicio(idOrden int primary key autoincrement, idCliente int not null, dniEmple text not null, idProducto int not null, fecRecogida text not null, fecEntrega text not null, hRecogida text not null, hEntrega text not null, FOREIGN KEY(idCliente) REFERENCES cliente(idCliente), FOREIGN KEY(dniEmple) REFERENCES empleado(dniEmple), FOREIGN KEY(idProducto) REFERENCES producto(idProducto))"

class SQLHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        println("***DB CREATED SUCCESSFULLY")
        db.execSQL(tCliente)
        //db.execSQL(tEmpleado)
        //db.execSQL(tProducto)
        //db.execSQL(tOrdenServicio)
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