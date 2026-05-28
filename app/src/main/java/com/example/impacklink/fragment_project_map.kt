package com.example.impacklink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ProjectMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_map, container, false)

        // Initialize Google Map fragment embedded inside this fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapContainer) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        val btnViewLocation = view.findViewById<Button>(R.id.btnViewLocation)
        btnViewLocation.setOnClickListener {
            if (::mMap.isInitialized) {
                // Focus camera view to a default location (e.g., New York coordinates)
                val projectLocation = LatLng(40.7128, -74.0060)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(projectLocation, 12f))
                Toast.makeText(context, "Centering on Project Location", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val defaultPoint = LatLng(40.7128, -74.0060)
        mMap.addMarker(MarkerOptions().position(defaultPoint).title("Project Hub Workspace"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultPoint))
    }
}