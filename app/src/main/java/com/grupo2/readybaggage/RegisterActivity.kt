package com.grupo2.readybaggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.startActivity
import com.grupo2.readybaggage.Utils.Companion.validationReg
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Registro
        regViewBtnReg.setOnClickListener() {
            if (validationReg(this)) {

                if (!ControlCliente.existeCliente(this,regViewTxtEmail.text.toString())){

                    if (ControlCliente.registrarCliente(this,  regViewTxtEmail.text.toString(), regViewTxtPass.text.toString(),regViewTxtNombre.text.toString(),regViewTxtApellidos.text.toString(),regViewTxtTelefono.text.toString())) {

                        startActivity<LoginActivity>()
                        Toast.makeText(this, "Registrado correctamente @"+regViewTxtNombre.text.toString(), Toast.LENGTH_LONG).show()

                    }//Si se registra
                    else {

                        Toast.makeText(this, "ERROR: Algo ha ido mal durante el registro, intentelo nuevamente", Toast.LENGTH_LONG).show()

                    }//Si ocurre un error

                }//Si no existe
                else {

                    Toast.makeText(this, "ERROR: Ya existe un cliente con ese email", Toast.LENGTH_LONG).show()

                }//Si ya existe

            }//Validacion de los datos

        }//onClick



        //Si ya tienes una cuenta
        regViewBtnLogin.setOnClickListener() {
            startActivity<LoginActivity>()

        }//onClick

    }//onCreate

}//RegisterActivity