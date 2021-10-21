package com.grupo2.readybaggage

data class Reserva(
    var idReserva: String,
    var idCliente: String,
    var idProducto: String,
    var cantidadProducto: String,
    var f_solicitud: String,
    var origen: String,
    var destino: String,
    var f_recogida: String,
    var f_entrega: String,
    var h_recogida: String,
    var h_entrega: String,
    var ubicacion: String
)