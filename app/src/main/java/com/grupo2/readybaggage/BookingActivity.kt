package com.grupo2.readybaggage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*
import java.text.DecimalFormat
import java.util.*

class BookingActivity : AppCompatActivity() {

    private var currentLanguage = Locale.getDefault()
    private lateinit var currentLang: String
    private var metodoPago: String? = null
    private var precioProducto: Int? = 0
    private val iva: Int = 21
    private var isNocturno: Boolean = false
    private val horaNocturna: Int = 21
    private val costeNocturno: Double = 4.0
    private var cMaletas: Int = 1
    val dec = DecimalFormat("#,###.00")

    private val hourInMinute = 60
    private val minHoursMargen = 120

    override fun onCreate(savedInstanceState: Bundle?) {

        currentLang=currentLanguage.toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val extras = intent.extras
        val productoId: Int? = extras?.getInt("productoId")
        val productoPrecio: Int? = extras?.getInt("productoPrecio")
        val productoNombre: String? = extras?.getString("productoNombre")

        bookingViewTxtHnocturno.text = getString(R.string.hnocturno) + " >"+ String.format("%02d:00", horaNocturna) + ": " + dec.format(costeNocturno) + "€"

        bookingViewTxtIva.text = getString(R.string.iva) + " " + iva + "%: "



        //Ir a contacto
        iconoContacto.setOnClickListener {
            startActivity<ContactoActivity>()
        }//onClick

        //Mostrar menu idiomas
        btIdiomas.setOnClickListener{
            Utils.showPopup(btIdiomas, this, this)
        }



        //Que tarifa ha sido seleccionada
        when(productoId){
            1 -> mostrarReserva(productoPrecio, productoNombre)
            2 -> mostrarReserva(productoPrecio, productoNombre)
            3 -> mostrarReserva(productoPrecio, productoNombre)
        }//when

        //Seleccion fecha y hora
        bookingViewEditFecReco.setOnClickListener() {
            showDatePickerDialog(1)

        }//onclick

        bookingViewEditFecEntrega.setOnClickListener() {
            showDatePickerDialog(2)

        }//onclick

        bookingViewEditHRecogida.setOnClickListener() {
            showTimePickerDialog(1)

        }//onclick

        bookingViewEditHEntrega.setOnClickListener() {
            showTimePickerDialog(2)

        }//onclick

        //Seleccion de metodo de pago
        bookingViewBtnPaypal.setOnClickListener() {
            metodoPago("Paypal")

        }//onclick

        bookingViewBtnApple.setOnClickListener() {
            metodoPago("Apple Pay")

        }//onclick

        bookingViewBtnMaster.setOnClickListener() {
            metodoPago("Master Card")

        }//onclick

        //Sumar cantidad de maletas
        bookingViewBtnPlus.setOnClickListener() {
            //cMaletas = bookingViewTxtMaletas.text.toString().toInt()
            cMaletas = cMaletas + 1
            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)

        }//onClick

        //Restar cantidad de maletas
        bookingViewBtnMinus.setOnClickListener() {
            cMaletas = cMaletas - 1

            if (cMaletas <= 0) {

                cMaletas = 1

            }// <= 0

            bookingViewTxtMaletas.text = cMaletas.toString()
            actualizarResumen(cMaletas)

        }//onClick

        //Comprobar datos
        bookingViewBtnReservar.setOnClickListener() {
            var errorAlReservar: Boolean = false

            if (bookingViewEditOrigen.text.toString().trim().isEmpty()) {

                bookingViewEditOrigen.setError("Escriba un lugar de Origen")
                errorAlReservar = true

            }//empty

            if (bookingViewEditDestino.text.toString().trim().isEmpty()) {

                bookingViewEditDestino.setError("Escriba un lugar de Destino")
                errorAlReservar = true

            }//empty

            if (bookingViewEditFecReco.text.toString().trim().isEmpty()) {

                bookingViewEditFecReco.setError("Seleccione una fecha de Recogida")
                errorAlReservar = true

            }//empty

            if (bookingViewEditFecEntrega.text.toString().trim().isEmpty()) {

                bookingViewEditFecEntrega.setError("Seleccione una fecha de Entrega")
                errorAlReservar = true

            }//empty

            if (bookingViewEditHRecogida.text.toString().trim().isEmpty()) {

                bookingViewEditHRecogida.setError("Seleccione una Hora de Recogida")
                errorAlReservar = true

            }//empty

            if (bookingViewEditHEntrega.text.toString().trim().isEmpty()) {

                bookingViewEditHEntrega.setError("Seleccione una Hora de Entrega")
                errorAlReservar = true

            }//empty

            if (metodoPago == null) {

                bookingViewTxtPayment.text = "Seleccione un metodo de pago"
                bookingViewTxtPayment.setTextColor(Color.parseColor("#FF0000"))
                errorAlReservar = true

            }//null

            if (!errorAlReservar) {

                startActivity<MainActivity>()
                var clienteObject: Cliente? = ControlCliente.getClienteObject()

                if (clienteObject != null) {

                    var idCliente: Int = clienteObject.idCliente.toInt()
                    var vMaletasCantidad : String = bookingViewTxtMaletas.text.toString()
                    var vOrigen : String = bookingViewEditOrigen.text.toString()
                    var vDestino : String = bookingViewEditDestino.text.toString()
                    var vFrecogida : String = bookingViewEditFecReco.text.toString()
                    var vFentrega : String = bookingViewEditFecEntrega.text.toString()
                    var vHrecogida : String = bookingViewEditHRecogida.text.toString()
                    var vHentrega : String = bookingViewEditHEntrega.text.toString()

                    if (ControlReserva.registrarReserva(this, idCliente,productoId!!,
                            vMaletasCantidad.toInt(),Utils.getCurrentDate(),vOrigen,vDestino,vFrecogida,vFentrega,vHrecogida,vHentrega,metodoPago!!)) {

                        println("[DEBUG] Reservar realizada correctamente")

                    }//reservado
                    else {

                        println("[ERROR] Problemas al realizazr la reserva")

                    }//no reservado

                }//cliente not null

            }//no errors

        }//onClick


        //Ir al perfil
        iconoPerfil.setOnClickListener {
            startActivity<ProfileActivity>()

        }//onClick

        //Ir al main
        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
        }//onClick

        //Ir a reservas
        iconoReservas.setOnClickListener {
            startActivity<ReservasActivity>()
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
        var preciosiniva: Double = (qMaletas * precioProducto!!).toDouble()
        var precioiva: Double = ((preciosiniva * iva).toDouble() / 100)
        var preciototal: Double = preciosiniva+precioiva
        if (isNocturno) preciototal = preciototal + costeNocturno

        bookingViewTxtSubtotal.text = getString(R.string.subtotal) + " " + dec.format(preciosiniva) + "€"
        bookingViewTxtIva.text = getString(R.string.iva) + " " + iva + "%: " + dec.format(precioiva) + "€"
        bookingViewTxtTotal.text = getString(R.string.total) + dec.format(preciototal) + "€"


    }//actualizarResumen

    //Mostrar fechas seleccionadas
    private fun showDatePickerDialog(elementId: Int) {

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year,elementId) }

        if (elementId == 1) {

            if (bookingViewEditFecEntrega.text.toString().trim() != "") {

                var (vDay,vMonth,vYear) = getDateValues(bookingViewEditFecEntrega.text.toString())
                datePicker.setDateMinMaxLimit(elementId,vDay,vMonth,vYear)

            }

        }

        if (elementId == 2) {

            if (bookingViewEditFecReco.text.toString().trim() != "") {

                var (vDay,vMonth,vYear) = getDateValues(bookingViewEditFecReco.text.toString())
                datePicker.setDateMinMaxLimit(elementId,vDay,vMonth,vYear)

            }//not empty

        }// = 2

        datePicker.show(supportFragmentManager, "datePicker")

    }//showDatePickerDialog

    //Fecha de recogida o entrega
    private fun onDateSelected(day: Int, month: Int, year: Int, elementoPicker: Int) {

        when (elementoPicker) {

            1 -> bookingViewEditFecReco.setText(String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + String.format("%04d", year))
            2 -> bookingViewEditFecEntrega.setText(String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + String.format("%04d", year))

        }//when

        fixEntregaTime()

    }//onDateSelected

    //Mostrar horas seleccionadas
    private fun showTimePickerDialog(elementId: Int) {

        val timePicker = TimePickerFragment { onTimeSelected(it, elementId) }
        timePicker.show(supportFragmentManager, "timePicker")

    }//showTimePickerDialog

    //Recoger la fecha elegida por el cliente
    private fun getDateValues(pDateString: String): Triple<Int, Int, Int> {

        val lstValues: List<Int> = pDateString.split("/").map { it -> it.trim().toInt() }
        return Triple(lstValues[0],lstValues[1],lstValues[2])

    }//getDateValues

    //Hora de recogida o entrega
    private fun onTimeSelected(time: String, elementoPicker: Int) {

        if (elementoPicker == 1) {

            bookingViewEditHRecogida.setText("$time")
            fixEntregaTime()

        }//if 1
        else {
            bookingViewEditHEntrega.setText("$time")
            fixEntregaTime()
        }//if not 1

    }//onTimeSelected

    /*Este metodo comprueba si la hora de entrega es inferior a la recogida, en tal caso, se
    comprobara la fehca, si las fecha son iguales, la fecha de entrega se aumentara en un dia.*/
    fun fixEntregaTime() {

        bookingViewEditHEntrega.setError(null)

        if (!bookingViewEditHEntrega.text.toString().isEmpty() && !bookingViewEditHRecogida.text.toString().isEmpty()) {

            if (bookingViewEditFecReco.text.toString().equals(bookingViewEditFecEntrega.text.toString())) {

                val lstHoraRecogida: List<Int> = bookingViewEditHRecogida.text.toString().split(":").map { it -> it.trim().toInt() }
                val lstHoraEntrega: List<Int> = bookingViewEditHEntrega.text.toString().split(":").map { it -> it.trim().toInt() }
                var timeRecogidaInMinutes: Int = (lstHoraRecogida[0]*hourInMinute) + (lstHoraRecogida[1])
                var timeEntregaInMinutes: Int = (lstHoraEntrega[0]*hourInMinute) + (lstHoraEntrega[1])

                if (timeEntregaInMinutes - timeRecogidaInMinutes < minHoursMargen) {

                    bookingViewEditHEntrega.setError("Debe haber al menos 2 horas de diferencia.")

                }//no cumple el minimo

            }//mismo dia

        }//not empty

        checkHorarioNocturno()

    }//fixEntregaTime

    //Comprobar si es horario nocturno
    fun checkHorarioNocturno() {

        bookingViewTxtHnocturno.visibility = View.GONE
        isNocturno = false

        if (!bookingViewEditHRecogida.text.toString().trim().isEmpty()) {

            val lstHoraRecogida: List<Int> = bookingViewEditHRecogida.text.toString().split(":").map { it -> it.trim().toInt() }

            if (lstHoraRecogida[0] >= horaNocturna) {

                bookingViewTxtHnocturno.visibility = View.VISIBLE
                isNocturno = true
                actualizarResumen(cMaletas)

            }//is nocturno

        }//not empty

    }//checkHorarioNocturno

}//BookingActivity
