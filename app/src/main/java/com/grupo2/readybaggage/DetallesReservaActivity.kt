package com.grupo2.readybaggage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.grupo2.readybaggage.Utils.Companion.startActivity
import kotlinx.android.synthetic.main.activity_detallesreserva.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*

class DetallesReservaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallesreserva)

        /*
            No añadiremos la carga del mapa de google en otro hilo de ejecucion dado
            que por su comportamiento, deducimos que ya hace uso de courutines.
            Por otra parte, estamos overrideando metodos y al usar courutines es tedioso
            lidiar con ciertos parametros que piden algunos de los metodos de dicha libreria
         */
        createMapFragment()


        var lastEstado: Int = 0

        if (!ControlCliente.isAdmin()) {

            dRvBtnModificarReserva.visibility = View.GONE
            dRvBtnModificarReserva.isEnabled = false
            dRvSpinnerEstado.setEnabled(false)
            dRvSpinnerEstado.setClickable(false)

        }//not admin

        val reservaEstados = arrayOf(getString(R.string.estado0),getString(R.string.estado1),getString(R.string.estado2),getString(R.string.estado3))
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, reservaEstados)
        dRvSpinnerEstado.adapter = adaptador

        val gson = Gson()
        val reserva = gson.fromJson<Reserva>(intent.getStringExtra("reserva"), Reserva::class.java)

        when(reserva.idProducto) {
            "1" -> dRvImgProducto.setImageResource(R.drawable.maleta_pequenia)
            "2" -> dRvImgProducto.setImageResource(R.drawable.maleta_grande)
            "3" -> dRvImgProducto.setImageResource(R.drawable.maleta_extra)
        }//when

        dRvTxtId.text = "ID: "+String.format("%06d", reserva.idReserva.toInt())
        dRvTxtFsolicitud.text = reserva.f_solicitud
        dRvEditNombre.setText(reserva.nombreCliente + " " + reserva.apellidoCliente)
        dRvEditFrecogida.setText(reserva.f_recogida)
        dRvEditFentrega.setText(reserva.f_entrega)
        dRvEditHrecogida.setText(reserva.h_recogida)
        dRvEditHentrega.setText(reserva.h_entrega)

        for (i in reservaEstados.indices) {

            var estadoFormated: String = reserva.estado.replace("estado","")
            var estadoFormatedToInt: Int = estadoFormated.toInt()

            if (i == estadoFormatedToInt) {

                lastEstado = i
                dRvSpinnerEstado.setSelection(i)
                break

            }

        }

        dRvBtnModificarReserva.setOnClickListener() {
            var newEstado: Int = dRvSpinnerEstado.selectedItemPosition

            if (newEstado != lastEstado) {

                reserva.estado = "estado"+newEstado

                if (ControlReserva.updateReserva(this,reserva)) {
<<<<<<< HEAD
                    Toast.makeText(this, "${getResources().getString(R.string.modificar_reserva)}", Toast.LENGTH_SHORT).show()
                    val reservasIntent = Intent(this, ReservasActivity::class.java)
                    reservasIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(reservasIntent)
                } else {
                    Toast.makeText(this, "${getResources().getString(R.string.errModificar_reserva)}", Toast.LENGTH_SHORT).show()
=======

                    Toast.makeText(this, "Reserva modificada correctamente", Toast.LENGTH_SHORT).show()
                    val reservasIntent = Intent(this, ReservasActivity::class.java)
                    reservasIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(reservasIntent)
                }
                else {
                    Toast.makeText(this, "Error al modificar la reserva", Toast.LENGTH_SHORT).show()
>>>>>>> 449a4eaf5de1795b405e1d1f1aa5ca25da555e6f
                }

            }

        }//onClick

       iconoContacto.setOnClickListener {
           startActivity<ContactoActivity>()
       }//onClick

        //Menu idiomas
        btIdiomas.setOnClickListener {
            Utils.showPopup(btIdiomas, this, this)
        }//onClick

        iconoReservas.setOnClickListener{
            startActivity<ReservasActivity>()
        }//onClick

        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
        }//onClick

        iconoPerfil.setOnClickListener {
            if (ControlCliente.getClienteName() != null) {
                startActivity<ProfileActivity>()
            }//logged
            else {
                startActivity<LoginActivity>()
            }//not logged
        }//onClick
    }


    private fun createMapFragment() {

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }//createMapFragment


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnCameraMoveStartedListener {
            dRvScrollviewMain.requestDisallowInterceptTouchEvent(true)

        }

        map.setOnCameraIdleListener {
            dRvScrollviewMain.requestDisallowInterceptTouchEvent(false)

        }
        createMarker()

    }//onMapReady

    private fun createMarker() {
        val favoritePlace = LatLng(43.262825437771625, -2.9351437151831314)
        map.addMarker(MarkerOptions().position(favoritePlace).title("DESTINO"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(favoritePlace, 14f), 1000, null)
    }//createMarker
}