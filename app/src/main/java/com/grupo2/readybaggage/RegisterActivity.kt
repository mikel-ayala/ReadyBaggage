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

        regViewBtnLogin.setOnClickListener() {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        regViewBtnReg.setOnClickListener() {
            var errorFound: Boolean = false
            //Email Validation
            if (!Utils.validarEmail(regViewTxtEmail.text.toString())) {
                regViewTxtEmail.setError("Introduzca un email valido")
                errorFound = true
            }
            //Password validation
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
            //Nombre Validation
            if (regViewTxtNombre.text.toString().isEmpty()) {
                regViewTxtNombre.setError("Este es un campo obligatorio")
                errorFound = true
            }

            //Telefono Validation
            if (!Utils.validarTelefono(regViewTxtTelefono.text.toString())) {
                regViewTxtTelefono.setError("Introduzca un telefono valido")
                errorFound = true
            }
            //Registro
            if (!errorFound) {
                if (!ControlCliente.existeCliente(this,regViewTxtEmail.text.toString())){
                    if (ControlCliente.registrarCliente(this,  regViewTxtEmail.text.toString(), regViewTxtPass.text.toString(),regViewTxtNombre.text.toString(),regViewTxtApellidos.text.toString(),regViewTxtTelefono.text.toString())) {
                        val MainActivity = Intent(this, MainActivity::class.java)
                        startActivity(MainActivity)
                        finish()
                        Toast.makeText(this, "Bienvenido @"+regViewTxtNombre.text.toString(), Toast.LENGTH_LONG).show()
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