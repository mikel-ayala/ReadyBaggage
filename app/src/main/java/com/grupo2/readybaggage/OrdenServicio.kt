package com.grupo2.readybaggage

data class OrdenServicio(
    var idOrden: String?,
    var idCliente: String?,
    var dniEmple: String?,
    var idProducto: String?,
    var f_solicitud: String?,
    var f_recogida: String?,
    var f_entrega: String?,
    var h_recogida: String?,
    var h_entrega: String?
)