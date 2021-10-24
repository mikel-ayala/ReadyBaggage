package com.grupo2.readybaggage

data class Reserva(
    var idReserva: String,
    var idCliente: String,
    var idProducto: String,
    var nombreCliente: String,
    var apellidoCliente: String,
    var cantidad: String,
    var f_solicitud: String,
    var origen: String,
    var destino: String,
    var f_recogida: String,
    var f_entrega: String,
    var h_recogida: String,
    var h_entrega: String,
    var metodoPago: String
    //var ubicacion: String
)