package com.grupo2.readybaggage

class Cliente (
    var dniEmple: String?,
    var email: String?,
    var nombre: String?,
    var apellidos: String?,
    var fecNac: String?,
    var direccion: String
    ) {
    fun copy(cCliente: Cliente) {
        this.dniEmple = cCliente.dniEmple
        this.email = cCliente.email
        this.nombre = cCliente.nombre
        this.apellidos = cCliente.apellidos
        this.fecNac = cCliente.fecNac
        this.direccion = cCliente.direccion
    }
    //fun copy(name: String = this.name, age: Int = this.age) = Empleado(name, age)
}