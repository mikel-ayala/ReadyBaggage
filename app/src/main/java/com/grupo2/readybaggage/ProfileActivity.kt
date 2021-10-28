package com.grupo2.readybaggage

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.grupo2.readybaggage.Utils.Companion.showPopup
import com.grupo2.readybaggage.Utils.Companion.startActivity
import com.grupo2.readybaggage.Utils.Companion.validationPro
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        currentLang=currentLanguage.toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        iconoContacto.setOnClickListener {
            startActivity<ContactoActivity>()
        }//onClick

        btIdiomas.setOnClickListener {
            showPopup(btIdiomas, this, this)
        }//onClick

        //Mostrar datos del usuario
        var cliente: Cliente? = ControlCliente.getClienteObject()
        var clienteUp: Cliente?

        profileViewTxtNombre.text = "${cliente?.nombre} ${cliente?.apellidos}"
        profileViewTextNombre.setText(cliente?.nombre)
        profileViewTextApellidos.setText(cliente?.apellidos)
        profileViewTextTelefono.setText(cliente?.telefono)
        profileViewTextEmail.setText(cliente?.email)
        profileViewTextPass.setText(cliente?.password)
        var value = cliente?.f_registro ?: ""
        val lstValues: List<String> = value.split("-").map { it -> it.trim() }
        profileViewTxtMemberTime.setText(getString(R.string.antiguedad) + lstValues[2]+"/"+lstValues[1]+"/"+lstValues[0])

        //Comprobar datos y actualizar usuario
        profileViewActualizar.setOnClickListener() {
            if (validationPro(this)){

                clienteUp = Cliente(cliente!!.idCliente,
                    profileViewTextEmail.text.toString(),
                    profileViewTextPass.text.toString(),
                    profileViewTextNombre.text.toString(),
                    profileViewTextApellidos.text.toString(),
                    profileViewTextTelefono.text.toString(),
                    cliente.f_registro, cliente.is_empleado)

                if (!cliente.equals(clienteUp)){

                    if (ControlCliente.updateCliente(this, clienteUp!!)) {

                        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_LONG).show()

                    }//Se ha podido actualizar
                    else {

                        Toast.makeText(this, "ERROR: No se ha podido actualizar", Toast.LENGTH_LONG).show()

                    }//No se ha podido actualizar

                }//Si los datos se han cambiado
                else {

                    Toast.makeText(this, "No han habido cambios para actualizar", Toast.LENGTH_LONG).show()

                }//Si los datos no se han cambiado

            }//Validacion de los datos

        }//onClick

        //Cerrar sesion
        profileViewBtnLogout.setOnClickListener() {
            if (ControlCliente.logout(this)) {

                Toast.makeText(this, "Se ha cerrado correctamente la sesion", Toast.LENGTH_LONG).show()
                startActivity<MainActivity>()

            }//logged out
            else {

                Toast.makeText(this, "ERROR: No se ha podido cerrar sesion", Toast.LENGTH_LONG).show()

            }//not logged out

        }//onClick


        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
        }//onClick

        iconoReservas.setOnClickListener{
            startActivity<ReservasActivity>()
        }//onClick

    }//onCreate

}//ProfileActivity