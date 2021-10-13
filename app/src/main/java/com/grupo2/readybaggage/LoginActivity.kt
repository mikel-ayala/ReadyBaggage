package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.Control
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewTextRegBtn.setOnClickListener() {
            val regIntent = Intent(this, RegisterActivity::class.java)
            startActivity(regIntent)
            finish()
            /*
            if (!ControlCliente.existeCliente(this, editUsername.text.toString())) {
                if (ControlCliente.aniadirCliente(this,  editUsername.text.toString(), editPass.text.toString())) {
                    Toast.makeText(this, "Cliente Registrado Correctamente", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ya existe un cliente con dicho nombre", Toast.LENGTH_LONG).show()
            }

             */
        }

        loginViewBtnLogin.setOnClickListener() {
            var loginErrorFound: Boolean = false
            //Email Validation
            if (!Utils.validarEmail(editUsername.text.toString())) {
                editUsername.setError("Introduzca un email valido")
                loginErrorFound = true
            }
            if (!Utils.validarPassword(editPass.text.toString())) {
                editPass.setError("Introduzca al menos 6 caracteres")
                loginErrorFound = true
            }
            if (!loginErrorFound) {
                //if (!ControlCliente.clienteIsLogged()) {
                    if (ControlCliente.logCliente(this,editUsername.text.toString(), editPass.text.toString())) {
                        val mainViewIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainViewIntent)
                        finish()
                        Toast.makeText(this, "Cliente logeado Correctamente", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "El usuario y/o la contraseña son incorrectas", Toast.LENGTH_LONG).show()
                    }
                //} else {
                    //val regIntent = Intent(this, RegisterActivity::class.java)
                    //startActivity(regIntent)
                    //finish()
                //}
            }
        }


    }
}