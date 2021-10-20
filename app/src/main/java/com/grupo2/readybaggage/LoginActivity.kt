package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.validationLog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Login
        loginViewBtnLogin.setOnClickListener() {
            //Comprobar los datos
            if (validationLog(this)) {

                if (ControlCliente.logCliente(this,logViewTextEmail.text.toString(), logViewTextPass.text.toString())) {

                    val mainViewIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainViewIntent)
                    finish()
                    Toast.makeText(this, "Cliente logeado Correctamente", Toast.LENGTH_LONG).show()

                }//Si inicia sesion
                else {

                Toast.makeText(this, "El usuario y/o la contraseña son incorrectas", Toast.LENGTH_LONG).show()

                }//Si no existe ese usuario

            }//Validacion de los datos

        }//onClick



        //Si no estas registrado
        loginViewTextRegBtn.setOnClickListener() {
            val regIntent = Intent(this, RegisterActivity::class.java)
            startActivity(regIntent)
            finish()

        }//onClick

    }//onCreate

}//LoginActivity