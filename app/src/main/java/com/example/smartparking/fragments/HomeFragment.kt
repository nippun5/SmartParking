package com.example.smartparking.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.smartparking.R
import com.example.smartparking.ui.DashboardActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task


class HomeFragment : Fragment(),OnMapReadyCallback {
     lateinit var currentLocation : Location
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    var REQUESTCODE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(view.context)
        fetchLastLocation()

        supportMapFragment!!.getMapAsync { googleMap ->
            // When map is loaded
            googleMap.setOnMapClickListener { latLng -> // When clicked on map
                // Initialize marker options
                val markerOptions = MarkerOptions()
                // Set position of marker
                markerOptions.position(latLng)
                // Set title of marker
                markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
                // Remove all marker
                googleMap.clear()
                // Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                // Add marker on map
                googleMap.addMarker(markerOptions)
            }
        }


        // Inflate the layout for this fragment
        return view
    }

    private fun fetchLastLocation() {
      if (view?.let {
              ActivityCompat.checkSelfPermission(
                  requireContext(),
                  Manifest.permission.ACCESS_FINE_LOCATION
              )
          } != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
              requireContext(),
               Manifest.permission.ACCESS_COARSE_LOCATION
           ) != PackageManager.PERMISSION_GRANTED
       ) {

           return
       }

          var task = fusedLocationProviderClient.getLastLocation()
        task.addOnSuccessListener(OnSuccessListener<Location>() {
            fun onSuccess( location: Location) {
                if (location!=null){
                    currentLocation = location;
                    Toast.makeText(context,"${currentLocation.latitude} ${currentLocation.longitude}",Toast.LENGTH_SHORT)
                    var supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
                    supportMapFragment.getMapAsync(this)
                }
            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        var latLng = LatLng(currentLocation.latitude,currentLocation.longitude)
        var markerOptions:MarkerOptions = MarkerOptions().position(latLng)
            .title("Here.")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5F))
        googleMap.addMarker(markerOptions)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUESTCODE -> {
                if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation()
                }


            }
        }
    }
}