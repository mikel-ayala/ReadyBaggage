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
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.activity_detallesreserva.*
import kotlinx.android.synthetic.main.menu_inferior.*
import kotlinx.android.synthetic.main.menu_superior.*

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

        val reservaEstados = arrayOf(getString(R.string.actualizar),"En almacen","En entrega","Entregado")
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, reservaEstados)
        dRvSpinnerEstado.adapter = adaptador

        val gson = Gson()
        val reserva = gson.fromJson<Reserva>(intent.getStringExtra("reserva"), Reserva::class.java)

        when(reserva.idProducto) {
            "1" -> dRvImgProducto.setImageResource(R.drawable.maleta_pequenia)
            "2" -> dRvImgProducto.setImageResource(R.drawable.maleta_grande)
            "3" -> dRvImgProducto.setImageResource(R.drawable.maleta_extra)
        }

        dRvTxtId.text = "ID: "+String.format("%06d", reserva.idReserva.toInt())
        dRvTxtFsolicitud.text = reserva.f_solicitud
        dRvEditNombre.setText(reserva.nombreCliente + " " + reserva.apellidoCliente)
        dRvEditFrecogida.setText(reserva.f_recogida)
        dRvEditFentrega.setText(reserva.f_entrega)
        dRvEditHrecogida.setText(reserva.h_recogida)
        dRvEditHentrega.setText(reserva.h_entrega)

        for (i in reservaEstados.indices) {
            var estadoFormated: String = reserva.estado.replace("estado_","")
            var estadoFormatedToInt: Int = estadoFormated.toInt()
            if (i == estadoFormatedToInt) {
                //dRvSpinnerEstado.setSelection(i)
                //dRvSpinnerEstado.selectedItemPosition
                dRvSpinnerEstado.setSelection(i)
                break
            }
        }

        dRvBtnModificarReserva.setOnClickListener() {
            if (!dRvSpinnerEstado.selectedItem.toString().equals(reserva.estado)) {
                reserva.estado = dRvSpinnerEstado.selectedItem.toString()
                if (ControlReserva.updateReserva(this,reserva)) {
                    Toast.makeText(this, "Reserva modificada correctamente", Toast.LENGTH_SHORT).show()
                    val reservasIntent = Intent(this, ReservasActivity::class.java)
                    reservasIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(reservasIntent)
                } else {
                    Toast.makeText(this, "Error al modificar la reserva", Toast.LENGTH_SHORT).show()
                }

            }
        }

        iconoMain.setOnClickListener {
            startActivity<MainActivity>()
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