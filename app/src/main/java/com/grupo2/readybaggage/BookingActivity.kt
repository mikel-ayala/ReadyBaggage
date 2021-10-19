package com.grupo2.readybaggage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.mainActivity
import com.grupo2.readybaggage.Utils.Companion.profileActivity
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*
import java.util.*

class BookingActivity : AppCompatActivity() {

    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String
    private var metodoPago: String? = null
    private var precioProducto: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        currentLang=currentLanguage.toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val extras = intent.extras
        val productoId: Int? = extras?.getInt("productoId")
        val productoPrecio: Int? = extras?.getInt("productoPrecio")
        val productoNombre: String? = extras?.getString("productoNombre")

        when(productoId){
            1 -> mostrarReserva(productoPrecio, productoNombre)
            2 -> mostrarReserva(productoPrecio, productoNombre)
            3 -> mostrarReserva(productoPrecio, productoNombre)
        }

        //Seleccion fecha y hora
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

        //Seleccion de metodo de pago
        bookingViewBtnPaypal.setOnClickListener() {
            metodoPago("Paypal")
        }

        bookingViewBtnApple.setOnClickListener() {
            metodoPago("Apple Pay")
        }

        bookingViewBtnMaster.setOnClickListener() {
            metodoPago("Master Card")
        }

        //Sumar cantidad de maletas
        bookingViewBtnPlus.setOnClickListener() {
            var cMaletas: Int = bookingViewTxtMaletas.text.toString().toInt()
            cMaletas = cMaletas + 1
            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)
        }

        //Restar cantidad de maletas
        bookingViewBtnMinus.setOnClickListener() {
            var cMaletas: Int = bookingViewTxtMaletas.text.toString().toInt()
            cMaletas = cMaletas - 1
            if (cMaletas <= 0) {
                cMaletas = 1
            }
            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)
        }

        //Mostrar menu idiomas
        btIdiomas.setOnClickListener{
            Utils.showPopup(btIdiomas, this, this)
        }

        //Comprobar datos
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
                bookingViewTxtPayment.text = "Seleccione un metodo de pago"
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

        iconoPerfil.setOnClickListener {
            profileActivity(this, this)
        }

        iconoMain.setOnClickListener {
            mainActivity(this, this)
        }

        iconoReservas.setOnClickListener {
            Toast.makeText(this, "Ver las reservas todavia no esta disponible", Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarReserva(precio: Int?, nombre: String?){
        precioProducto = precio
        bookingViewTxtProductName.text = nombre
        when(precio){
            8 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_pequenia)
            10 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_grande)
            12 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_extra)
        }
        bookingViewTxtProductPrice.text = precioProducto.toString() + "€"
        actualizarResumen(1)
    }

    private fun metodoPago(metodo: String){
        bookingViewTxtPayment.text = getString(R.string.metodo_pago) + metodo
        metodoPago = metodo
        bookingViewTxtPayment.setTextColor(Color.parseColor("#4e4e4e"))
    }

    //Actualizar cantidad
    private fun actualizarResumen(qMaletas: Int) {
        bookingViewTxtTotalMaletas.text = getString(R.string.n_maletas) + qMaletas.toString()
        bookingViewTxtTotal.text = "Total: " + (qMaletas * precioProducto!!) + "€"
    }

    //Mostrar fechas y horas seleccionadas
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
}
