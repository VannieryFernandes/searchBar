package com.projeto.bar.searchBar

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.projeto.bar.searchBar.ConnectivityReceiver

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var nomeBarText: TextView
    var br = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        nomeBarText = findViewById<TextView>(R.id.nomeBarText)
        registerReceiver(br, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        var i = this.intent

        var nomeBar = i.getExtras().getString("NAME_BAR")
        var lat= i.getExtras().getString("LAT")
        var lng= i.getExtras().getString("LNG")

        nomeBarText.setText(nomeBar)

        val latitude: Double = lat.toDouble()
        val longitude:Double = lng.toDouble()

        val bar = LatLng(latitude, longitude)
        mMap.addMarker(createMarkers(bar,nomeBar))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bar,18.5f))
    }

    fun createMarkers(latLng: LatLng, title:String): MarkerOptions{
        return MarkerOptions()
                .position(latLng)
                .title(title)


    }
}
