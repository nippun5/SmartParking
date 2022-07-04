package com.example.smartparking.fragments

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.smartparking.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class HomeFragment : Fragment(),OnMapReadyCallback {
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
  var latitude:String=""
  var longitude:String=""
    lateinit var gMap : GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?


        supportMapFragment?.getMapAsync(this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        getLastLocation()




        // Inflate the layout for this fragment
        return view
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {

                        latitude=location.latitude.toString()
                        longitude= location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(requireActivity(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }


    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }


    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            var mLastLocation: Location? = locationResult.lastLocation
            latitude=mLastLocation?.latitude.toString()
            longitude=mLastLocation?.longitude.toString()

        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

//        var latLng=LatLng(latitude.toDouble(), longitude.toDouble())
        var latLng=LatLng(27.703668, 85.309368)


            // Initialize marker options
            val markerOptions = MarkerOptions()
            // Set position of marker
            markerOptions.position(latLng)
            // Set title of marker
            markerOptions.title(latitude.toString() + " : " + longitude)
            // Remove all marker
            googleMap.clear()
            // Animating to zoom the marker
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f))
            // Add marker on map
            googleMap.addMarker(markerOptions)

        println(latLng)
        Log.d("MapLocationLatitude", "onMapReady: $latLng")
        googleMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener{
            override fun onMarkerClick(p0: Marker): Boolean {

                return true
            }


        })
    }

    }
