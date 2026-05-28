package com.example.impacklink

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class VolunteerProjectMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_project_map)

        // Initialize Google Map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapContainer) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        val btnViewLocation = findViewById<Button>(R.id.btnViewLocation)
        btnViewLocation.setOnClickListener {
            if (::mMap.isInitialized) {
                // Focus camera view to a default location (e.g., New York coordinates)
                val projectLocation = LatLng(40.7128, -74.0060)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(projectLocation, 12f))
                Toast.makeText(this, "Centering on Project Location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val defaultPoint = LatLng(40.7128, -74.0060)
        mMap.addMarker(MarkerOptions().position(defaultPoint).title("Project Hub Workspace"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultPoint))
    }
}
