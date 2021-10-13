package com.grupo2.readybaggage

class Cliente (
    var idCliente: String?,
    var email: String?,
    var password: String?,
    var nombre: String?,
    var apellidos: String?,
    var fecNac: String?,
    var telefono: String
    ) {
    fun copy(cCliente: Cliente) {
        this.idCliente = cCliente.idCliente
        this.email = cCliente.email
        this.password = cCliente.password
        this.nombre = cCliente.nombre
        this.apellidos = cCliente.apellidos
        this.fecNac = cCliente.fecNac
        this.telefono = cCliente.telefono
    }
    //fun copy(name: String = this.name, age: Int = this.age) = Empleado(name, age)
}