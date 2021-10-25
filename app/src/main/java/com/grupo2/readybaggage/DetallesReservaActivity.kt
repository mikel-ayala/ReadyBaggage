package com.grupo2.readybaggage

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
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.activity_detallesreserva.*

class DetallesReservaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallesreserva)
        createMapFragment()

        if (!ControlCliente.isAdmin()) {
            //bookingViewTxtIva.isEnabled = false
            //bookingViewTxtIva.visibility = View.GONE
            dRvBtnModificarReserva.visibility = View.GONE
            dRvBtnModificarReserva.isEnabled = false
            dRvSpinnerEstado.setEnabled(false);
            dRvSpinnerEstado.setClickable(false);
        }

        val operaciones = arrayOf("Pendiente","En almacen","En entrega","Entregado")
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, operaciones)
        dRvSpinnerEstado.adapter = adaptador

        val gson = Gson()
        val reserva = gson.fromJson<Reserva>(intent.getStringExtra("reserva"), Reserva::class.java)

        dRvTxtId.text = "ID: "+String.format("%06d", reserva.idReserva.toInt())
        dRvTxtFsolicitud.text = reserva.f_solicitud
        dRvEditNombre.setText(reserva.nombreCliente + " " + reserva.apellidoCliente)
        dRvEditFrecogida.setText(reserva.f_recogida)
        dRvEditFentrega.setText(reserva.f_entrega)
        dRvEditHrecogida.setText(reserva.h_recogida)
        dRvEditHentrega.setText(reserva.h_entrega)



        dRvBtnModificarReserva.setOnClickListener() {
            if (!dRvSpinnerEstado.selectedItem.toString().equals(reserva.estado)) {
                reserva.estado = dRvSpinnerEstado.selectedItem.toString()
                if (ControlReserva.updateReserva(this,reserva)) {
                    Toast.makeText(this, "Reserva modificada correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al modificar la reserva", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val favoritePlace = LatLng(43.25782365561766, -2.902841474811389)
        map.addMarker(MarkerOptions().position(favoritePlace).title("DESTINO"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f), 1000, null)
    }
}