package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profiletmp.*

class ActivityPorfileTemp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiletmp)
        var pViewCliente: Cliente? = ControlCliente.getClienteObject()
        if (pViewCliente != null) {
            profileViewTxtNombre.text = pViewCliente.nombre+ " " +pViewCliente.apellidos
            profileViewEditNombre.setText(pViewCliente.nombre)
            profileViewEditApellidos.setText(pViewCliente.apellidos)
            profileViewEditTelefono.setText(pViewCliente.telefono)
            profileViewEditEmail.setText(pViewCliente.email)
            profileViewEditPassword.setText(pViewCliente.password)

        }

        profileViewActualizar.setOnClickListener() {
            if ( pViewCliente!= null){
                if (
                    !profileViewEditNombre.text.toString().equals(pViewCliente.nombre) ||
                    !profileViewEditApellidos.text.toString().equals(pViewCliente.apellidos) ||
                    !profileViewEditTelefono.text.toString().equals(pViewCliente.telefono) ||
                    !profileViewEditEmail.text.toString().equals(pViewCliente.email) ||
                    !profileViewEditPassword.text.toString().equals(pViewCliente.password)

                ) {
                    pViewCliente.nombre = profileViewEditNombre.text.toString()
                    pViewCliente.apellidos = profileViewEditApellidos.text.toString()
                    pViewCliente.telefono = profileViewEditTelefono.text.toString()
                    pViewCliente.email = profileViewEditEmail.text.toString()
                    pViewCliente.password = profileViewEditPassword.text.toString()
                    if (ControlCliente.modificarCliente(this,pViewCliente)) {

                        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "ERROR: No se ha podido actualizar", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "No ha habido cambios para actualizar", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "ERROR: No se ha podido actualizar", Toast.LENGTH_LONG).show()
            }
        }

        profileViewBtnLogout.setOnClickListener() {
            if (ControlCliente.logout()) {
                Toast.makeText(this, "Se ha cerrado correctamente la sesion", Toast.LENGTH_LONG).show()
                val mainViewIntent = Intent(this, MainActivity::class.java)
                startActivity(mainViewIntent)
                finish()
            } else {
                Toast.makeText(this, "ERROR: No se ha podido cerrar sesion", Toast.LENGTH_LONG).show()
            }
        }


    }
}