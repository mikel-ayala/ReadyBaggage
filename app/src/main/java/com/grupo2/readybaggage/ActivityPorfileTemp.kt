package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.showPopup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profiletmp.*
import kotlinx.android.synthetic.main.activity_profiletmp.btIdiomas
import java.util.*

class ActivityPorfileTemp : AppCompatActivity() {
    private lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String

    override fun onCreate(savedInstanceState: Bundle?) {
        currentLang=currentLanguage.toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiletmp)
        //Mostrar datos del usuario
        var pViewCliente: Cliente? = ControlCliente.getClienteObject()
        if (pViewCliente != null) {
            profileViewTxtNombre.text = pViewCliente.nombre+ " " +pViewCliente.apellidos
            profileViewEditNombre.setText(pViewCliente.nombre)
            profileViewEditApellidos.setText(pViewCliente.apellidos)
            profileViewEditTelefono.setText(pViewCliente.telefono)
            profileViewEditEmail.setText(pViewCliente.email)
            profileViewEditPassword.setText(pViewCliente.password)

        }

        //Comprobar datos y actualizar usuario
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
        //Cerrar sesion
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

        btIdiomas.setOnClickListener{
            showPopup(btIdiomas, this, this)
        }
    }
}