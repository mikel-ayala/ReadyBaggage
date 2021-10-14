package com.grupo2.readybaggage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profiletmp.*
import kotlinx.android.synthetic.main.activity_profiletmp.btIdiomas
import java.util.*

class BookingActivity : AppCompatActivity() {
    private lateinit var locale: Locale
    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String
    private var metodoPago: String? = null
    private var precioProducto: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        currentLang=currentLanguage.toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val profileName=intent.getStringExtra("productoId")
        if (profileName != null) {
             if (profileName.toString().equals("1")) {
                 precioProducto = 8
                 bookingViewTxtProductName.text = "Maletas hasta 10kg"
                 bookingViewImgProduct.setImageResource(R.drawable.maleta_pequenia)
             } else if (profileName.toString().equals("2")) {
                 precioProducto = 10
                 bookingViewTxtProductName.text = "Maletas a partir de 10kg"
                 bookingViewImgProduct.setImageResource(R.drawable.maleta_grande)
             } else if (profileName.toString().equals("3")) {
                 precioProducto = 12
                 bookingViewTxtProductName.text = "Maletas a partir de 20kg"
                 bookingViewImgProduct.setImageResource(R.drawable.maleta_extra)
             }

            bookingViewTxtProductPrice.text = precioProducto.toString()+"€"
            actualizarResumen(1)
        }
        bookingViewBtnReservar.setOnClickListener() {
            var bookingReservarError = false
            if (bookingViewEditOrigen.text.toString().isEmpty()) {
                bookingViewEditOrigen.setError("Introduzca un Origen")
                bookingReservarError = true
            }

            if (bookingViewEditDestino.text.toString().isEmpty()) {
                bookingViewEditDestino.setError("Introduzca un Destino")
                bookingReservarError = true
            }

            if (metodoPago == null) {
                Toast.makeText(this, "ERROR: Introduzca un Metodo de pago", Toast.LENGTH_LONG).show()
                bookingReservarError = true
            }


        }

        bookingViewEditFecReco.setOnClickListener() {
            showDatePickerDialog(1)
        }
        bookingViewEditFecEntrega.setOnClickListener() {
            showDatePickerDialog(2)
        }

        bookingViewEditHRecogida.setOnClickListener() {
            showTimePickerDialog(1)
        }

        bookingViewEditHEntrega.setOnClickListener() {
            showTimePickerDialog(2)
        }

        bookingViewBtnPaypal.setOnClickListener() {
            bookingViewTxtPayment.text = "Metodo de pago: Paypal"
            metodoPago = "Paypal"
            bookingViewTxtPayment.setTextColor(Color.parseColor("#4e4e4e"))
        }

        bookingViewBtnApple.setOnClickListener() {
            bookingViewTxtPayment.text = "Metodo de pago: Apple Pay"
            metodoPago = "Apple Pay"
            bookingViewTxtPayment.setTextColor(Color.parseColor("#4e4e4e"))
        }

        bookingViewBtnMaster.setOnClickListener() {
            bookingViewTxtPayment.text = "Metodo de pago: Master Card"
            metodoPago = "Master Card"
            bookingViewTxtPayment.setTextColor(Color.parseColor("#4e4e4e"))
        }

        bookingViewBtnPlus.setOnClickListener() {
            var cMaletas: Int = bookingViewTxtMaletas.text.toString().toInt()
            cMaletas = cMaletas + 1
            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)
        }

        bookingViewBtnMinus.setOnClickListener() {
            var cMaletas: Int = bookingViewTxtMaletas.text.toString().toInt()
            cMaletas = cMaletas - 1
            if (cMaletas <= 0) {
                cMaletas = 1
            }
            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)
        }


        btIdiomas.setOnClickListener{
            showPopup(btIdiomas)
        }

        bookingViewBtnReservar.setOnClickListener() {
            var errorAlReservar: Boolean = false
            if (bookingViewEditOrigen.text.toString().isEmpty()) {
                bookingViewEditOrigen.setError("Escriba un lugar de Origen")
                errorAlReservar = true
            }

            if (bookingViewEditDestino.text.toString().isEmpty()) {
                bookingViewEditDestino.setError("Escriba un lugar de Destino")
                errorAlReservar = true
            }

            if (bookingViewEditFecReco.text.toString().isEmpty()) {
                bookingViewEditFecReco.setError("Seleccione una fecha de Recogida")
                errorAlReservar = true
            }

            if (bookingViewEditFecEntrega.text.toString().isEmpty()) {
                bookingViewEditFecEntrega.setError("Seleccione una fecha de Entrega")
                errorAlReservar = true
            }

            if (bookingViewEditHRecogida.text.toString().isEmpty()) {
                bookingViewEditHRecogida.setError("Seleccione una Hora de Recogida")
                errorAlReservar = true
            }

            if (bookingViewEditHEntrega.text.toString().isEmpty()) {
                bookingViewEditHEntrega.setError("Seleccione una Hora de Entrega")
                errorAlReservar = true
            }

            if (metodoPago == null) {
                bookingViewTxtPayment.text = "*Seleccione un metodo de pago"
                bookingViewTxtPayment.setTextColor(Color.parseColor("#FF0000"))
                //Toast.makeText(this, "Error: Seleccione un Metodo de Pago", Toast.LENGTH_LONG).show()
                errorAlReservar = true
            }

            if (!errorAlReservar) {
                Toast.makeText(this, "EXITO: Reserva realizada correctamente", Toast.LENGTH_LONG).show()
                val homeViewIntent = Intent(this, MainActivity::class.java)
                startActivity(homeViewIntent)
                finish()
            }

        }
    }

    private fun actualizarResumen(qMaletas: Int) {
        bookingViewTxtTotalMaletas.text = "Numero de maletas: "+qMaletas.toString()
        bookingViewTxtTotal.text = "Total: "+(qMaletas*precioProducto)+"€"
    }

    private fun showDatePickerDialog(elementId: Int) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year,elementId) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int, elementoPicker: Int) {
        if (elementoPicker == 1) {
            bookingViewEditFecReco.setText("$day/$month/$year")
        } else {
            bookingViewEditFecEntrega.setText("$day/$month/$year")
        }

    }

    private fun showTimePickerDialog(elementId: Int) {
        val timePicker = TimePickerFragment { onTimeSelected(it, elementId) }
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun onTimeSelected(time: String, elementoPicker: Int) {
        if (elementoPicker == 1) {
            bookingViewEditHRecogida.setText("$time")
        } else {
            bookingViewEditHEntrega.setText("$time")
        }
    }

    private fun showPopup(v : View){
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_idioma, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.spanish-> {
//                    SharedApp.prefs.dato="es"
                    setLocale("es")
                }
                R.id.euskera-> {
//                    SharedApp.prefs.dato="eu"
                    setLocale("eu")
                }
                R.id.english-> {
//                    SharedApp.prefs.dato="en"
                    setLocale("en")
                }
            }
            true
        }
        popup.show()
    }

    //cambiar idioma
    private fun setLocale(localeName: String) {
        locale = Locale(localeName)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(
            this,
            this::class.java
        )
//            refresh.putExtra(currentLang, localeName)
        refresh.putExtra(currentLang, localeName)
        finish()
        startActivity(refresh)
    }
}