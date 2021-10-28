package com.grupo2.readybaggage

data class Cliente (
    var idCliente: String,
    var email: String,
    var password: String,
    var nombre: String,
    var apellidos: String,
    var telefono: String,
    var f_registro: String,
    var is_empleado: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cliente
        println("Other es un cliente")
        if (idCliente != other.idCliente) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (nombre != other.nombre) return false
        if (apellidos != other.apellidos) return false
        if (telefono != other.telefono) return false
        if (f_registro != other.f_registro) return false
        if (is_empleado != other.is_empleado) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idCliente.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + nombre.hashCode()
        result = 31 * result + apellidos.hashCode()
        result = 31 * result + telefono.hashCode()
        result = 31 * result + f_registro.hashCode()
        result = 31 * result + is_empleado.hashCode()
        return result
    }
}