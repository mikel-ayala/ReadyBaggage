package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewBtnReg.setOnClickListener() {
            if (!ControlCliente.existeCliente(this, editUsername.text.toString())) {
                if (ControlCliente.aniadirCliente(this,  editUsername.text.toString(),  editPass.text.toString())) {
                    Toast.makeText(this, "Cliente Registrado Correctamente", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ya existe un cliente con dicho nombre", Toast.LENGTH_LONG).show()
            }

        }
    }
}