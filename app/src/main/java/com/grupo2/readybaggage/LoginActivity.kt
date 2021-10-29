package com.grupo2.readybaggage

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.startActivity
import com.grupo2.readybaggage.Utils.Companion.validationLog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    var contextActivity: Context = this
    var vActivity: Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Cargar Data User
        var userEmailData: String? = Preferences.getUserPreferences(this,"userdata","email")
        if (userEmailData != null && userEmailData.trim() != "") {
            logViewTextEmail.setText(userEmailData)
        }

        //Login
        loginViewBtnLogin.setOnClickListener() {
            //Comprobar los datos

            if (validationLog(this)) {
                if (ControlCliente.logCliente(contextActivity,findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.logViewTextEmail).text.toString(), findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.logViewTextPass).text.toString())) {
                    if (Preferences.setUserPreferences(vActivity, "userdata","email",logViewTextEmail.text.toString()) && Preferences.setUserPreferences(vActivity, "userdata","password",logViewTextPass.text.toString())) {
                        print("[DEBUG] User data guardada correctamente")
                    } else {
                        print("[ERROR] Error al guardar el email del usuario")
                    }
                    startActivity<MainActivity>()
                    Toast.makeText(contextActivity, "Cliente logeado Correctamente", Toast.LENGTH_LONG).show()

                }//Si inicia sesion
                else {

                Toast.makeText(contextActivity, "El usuario y/o la contraseña son incorrectas", Toast.LENGTH_LONG).show()

                }//Si no existe ese usuario

            }//Validacion de los datos

        }//onClick

        //Si no estas registrado
        loginViewTextRegBtn.setOnClickListener() {
            startActivity<RegisterActivity>()
        }//onClick

    }//onCreate

}//LoginActivity