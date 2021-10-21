package com.grupo2.readybaggage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.grupo2.readybaggage.Utils.Companion.startActivity
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

        //Ir al main
        iconoMain.setOnClickListener {
            startActivity<MainActivity>()

        }//onClick

        //Mostrar menu idiomas
        btIdiomas.setOnClickListener{
            Utils.showPopup(btIdiomas, this, this)
        }

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

        //Comprobar datos
        bookingViewBtnReservar.setOnClickListener() {
            var errorAlReservar: Boolean = false
            if (bookingViewEditOrigen.text.toString().trim().isEmpty()) {
                bookingViewEditOrigen.setError("Escriba un lugar de Origen")
                errorAlReservar = true
            }

            if (bookingViewEditDestino.text.toString().trim().isEmpty()) {
                bookingViewEditDestino.setError("Escriba un lugar de Destino")
                errorAlReservar = true
            }

            if (bookingViewEditFecReco.text.toString().trim().isEmpty()) {
                bookingViewEditFecReco.setError("Seleccione una fecha de Recogida")
                errorAlReservar = true
            }

            if (bookingViewEditFecEntrega.text.toString().trim().isEmpty()) {
                bookingViewEditFecEntrega.setError("Seleccione una fecha de Entrega")
                errorAlReservar = true
            }

            if (bookingViewEditHRecogida.text.toString().trim().isEmpty()) {
                bookingViewEditHRecogida.setError("Seleccione una Hora de Recogida")
                errorAlReservar = true
            }

            if (bookingViewEditHEntrega.text.toString().trim().isEmpty()) {
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
                startActivity<MainActivity>()

            }
        }

        //Ir al perfil
        iconoPerfil.setOnClickListener {
            startActivity<ProfileActivity>()

        }//onClick

        //Ir a reservas
        iconoReservas.setOnClickListener {
            Toast.makeText(this, "Ver las reservas todavia no esta disponible", Toast.LENGTH_LONG).show()

        }//onClick

    }//onCreate

    private fun mostrarReserva(precio: Int?, nombre: String?){

        precioProducto = precio
        bookingViewTxtProductName.text = nombre

        when(precio){

            8 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_pequenia)
            10 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_grande)
            12 -> bookingViewImgProduct.setImageResource(R.drawable.maleta_extra)

        }//when precio

        bookingViewTxtProductPrice.text = precioProducto.toString() + "€"
        actualizarResumen(1)

    }//mostrarReserva

    private fun metodoPago(metodo: String){

        bookingViewTxtPayment.text = getString(R.string.metodo_pago) + metodo
        metodoPago = metodo
        bookingViewTxtPayment.setTextColor(Color.parseColor("#4e4e4e"))

    }//metodoPago

    //Actualizar cantidad
    private fun actualizarResumen(qMaletas: Int) {

        bookingViewTxtTotalMaletas.text = getString(R.string.n_maletas) + qMaletas.toString()
        bookingViewTxtTotal.text = "Total: " + (qMaletas * precioProducto!!) + "€"

    }//actualizarResumen

    //Mostrar fechas seleccionadas
    private fun showDatePickerDialog(elementId: Int) {

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year,elementId) }
        datePicker.show(supportFragmentManager, "datePicker")

    }//showDatePickerDialog

    //Fecha de recogida o entrega
    private fun onDateSelected(day: Int, month: Int, year: Int, elementoPicker: Int) {

        if (elementoPicker == 1) {

            bookingViewEditFecReco.setText("$day/$month/$year")

        }//if 1
        else {

            bookingViewEditFecEntrega.setText("$day/$month/$year")

        }//if not 1

    }//onDateSelected

    //Mostrar horas seleccionadas
    private fun showTimePickerDialog(elementId: Int) {
        val timePicker = TimePickerFragment { onTimeSelected(it, elementId) }
        timePicker.show(supportFragmentManager, "timePicker")
    }//showTimePickerDialog

    //Hora de recogida o entrega
    private fun onTimeSelected(time: String, elementoPicker: Int) {
        if (elementoPicker == 1) {
            bookingViewEditHRecogida.setText("$time")
        }//if 1
        else {
            bookingViewEditHEntrega.setText("$time")
        }//if not 1

    }//onTimeSelected

}//BookingActivity
