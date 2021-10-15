package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.Control
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Si ya tienes una cuenta
        regViewBtnLogin.setOnClickListener() {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        //Proceso de registro
        regViewBtnReg.setOnClickListener() {
            var errorFound: Boolean = false
            //Validar el email
            if (!Utils.validarEmail(regViewTxtEmail.text.toString())) {
                regViewTxtEmail.setError("Introduzca un email valido")
                errorFound = true
            }
            //Validar la password
            if (!Utils.validarPassword(regViewTxtPass.text.toString())) {
                regViewTxtPass.setError("Introduzca al menos 6 caracteres")
                //regViewTxtPassConfirm.setError("Introduzca al menos 6 caracteres")
                errorFound = true
            }
            /*
            if (!regViewTxtPass.text.toString().equals(regViewTxtPassConfirm.text.toString())) {
                regViewTxtPass.setError("La contraseña no coincide")
                regViewTxtPassConfirm.setError("La contraseña no coincide")
                errorFound = true
            }
             */
            //Validar el nombre
            if (regViewTxtNombre.text.toString().isEmpty()) {
                regViewTxtNombre.setError("Este es un campo obligatorio")
                errorFound = true
            }

            //Validar el telefono
            if (!Utils.validarTelefono(regViewTxtTelefono.text.toString())) {
                regViewTxtTelefono.setError("Introduzca un telefono valido")
                errorFound = true
            }
            //Comprobar los datos
            if (!errorFound) {
                if (!ControlCliente.existeCliente(this,regViewTxtEmail.text.toString())){
                    if (ControlCliente.registrarCliente(this,  regViewTxtEmail.text.toString(), regViewTxtPass.text.toString(),regViewTxtNombre.text.toString(),regViewTxtApellidos.text.toString(),regViewTxtTelefono.text.toString())) {
                        val loginIntent = Intent(this, LoginActivity::class.java)
                        startActivity(loginIntent)
                        finish()
                        Toast.makeText(this, "Registrado correctamente @"+regViewTxtNombre.text.toString(), Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this, "ERROR: Algo ha ido mal durante el registro, intentelo nuevamente", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "ERROR: Ya existe un cliente con ese email", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}