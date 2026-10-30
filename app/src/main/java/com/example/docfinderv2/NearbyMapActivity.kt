package com.example.docfinderv2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class NearbyMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var allDoctors: List<Doctor>

    private val locationPermissionRequest = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) enableMyLocation()
        // If denied, the map still shows all doctor markers — just without centering on the user.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_map)

        allDoctors = HomeActivity.sampleDoctorsPublic()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupBottomNav()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        addDoctorMarkers()
        requestLocationPermissionAndCenter()

        googleMap.setOnMarkerClickListener { marker ->
            val doctorId = marker.tag as? Int
            val doctor = allDoctors.firstOrNull { it.id == doctorId }
            if (doctor != null) {
                val intent = Intent(this, DoctorDetailsActivity::class.java)
                intent.putExtra(DoctorDetailsActivity.EXTRA_DOCTOR, doctor)
                startActivity(intent)
            }
            false // false = also show the default info window / camera behavior
        }
    }

    private fun addDoctorMarkers() {
        if (allDoctors.isEmpty()) return

        val boundsBuilder = LatLngBounds.Builder()

        allDoctors.forEach { doctor ->
            val position = LatLng(doctor.latitude, doctor.longitude)
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(doctor.name)
                    .snippet("${doctor.specialization} • ${doctor.location}")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
            marker?.tag = doctor.id
            boundsBuilder.include(position)
        }

        // Fit the camera to show every doctor marker at once
        val bounds = boundsBuilder.build()
        googleMap.setOnMapLoadedCallback {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 120))
        }
    }

    private fun requestLocationPermissionAndCenter() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> enableMyLocation()

            else -> locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Only recenter on the user if we're not already fitting all doctor markers meaningfully close by
                val userLatLng = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 12f))
            }
        }
    }

    private fun setupBottomNav() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_search
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_search -> true
                R.id.nav_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    true
                }
                R.id.nav_about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
