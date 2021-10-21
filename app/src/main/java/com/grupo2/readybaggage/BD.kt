package com.grupo2.readybaggage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class bd(context: Context, name:String, factory: SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context,name,factory,version) {

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("create table cliente(idCliente real PRIMARY KEY AUTOINCREMENT, email text UNIQUE NOT NULL, pass text not null, nombre texT not null, apellidos text, telefono text not null, f_registro text DEFAULT CURRENT_DATE, is_empleado real DEFAULT 0)")
        db.execSQL("create table producto(idProducto real PRIMARY KEY AUTOINCREMENT, nombre text not null, descripcion text not null, precio real not null)")
        db.execSQL("create table reserva(idReserva real PRIMARY KEY AUTOINCREMENT, idCliente real not null, idProducto real not null, cantidadProducto real not null, fSolicitud text not null, origen text not null, destino text not null, fRecogida text not null, fEntrega text not null, hRecogida text not null, hEntrega text not null, ubicacion text not null, FOREIGN KEY(idCliente) REFERENCES cliente(idCliente), FOREIGN KEY(idProducto) REFERENCES producto(idProducto))")

    }//onCreate

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db!!.execSQL("drop table if exists cliente")
        db.execSQL("drop table if exists producto")
        db.execSQL("drop table if exists reserva")
        onCreate(db)

    }//onUpgrade
}//bd
