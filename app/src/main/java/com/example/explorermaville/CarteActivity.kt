package com.example.explorermaville

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CarteActivity : AppCompatActivity(), OnMapReadyCallback, SensorEventListener {

    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    private val shakeThreshold = 800 // Ajustez ce seuil selon votre besoin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carte)

        val btnListActivities = findViewById<Button>(R.id.btn_list_activities)
        btnListActivities.setOnClickListener {
            // Récupérer la position actuelle de l'utilisateur
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Si la permission est accordée, affichez votre position actuelle sur la carte
                showCurrentLocation()
            } else {
                // Sinon, demandez la permission de localisation à l'utilisateur
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION
                )
            }
        }

        // Obtenez une référence au SupportMapFragment et appelez la méthode getMapAsync
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        // Initialisez le FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialisez le SensorManager et le capteur d'accélération
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // La carte GoogleMap est prête à être utilisée
        mMap = googleMap
    }

    private fun showCurrentLocation() {
        // Récupérez la position actuelle de l'utilisateur
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Ajoutez un marqueur sur la carte pour afficher la position actuelle
                val latLng = LatLng(location.latitude, location.longitude)

                if (mMap != null) {
                    mMap?.addMarker(MarkerOptions().position(latLng).title("Ma position"))
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    Log.d("CarteActivity", "Marqueur ajouté avec succès")

                    // Lancer l'activité ListedActivitiesActivity avec les valeurs de latitude et de longitude
                    val intent = Intent(this, ListedActivitiesActivity::class.java)
                    intent.putExtra("latitude", location.latitude)
                    intent.putExtra("longitude", location.longitude)
                    intent.putExtra("latLng", latLng)
                    Log.wtf("wtf", "Latitude intialiser à l'intent : $location.latitude")
                    Log.wtf("wtf", "Longitude intialiser à l'intent : $location.longitude")
                    Log.wtf("wtf", "LatLng intialiser à l'intent : $latLng")

                    startActivity(intent)
                } else {
                    Log.e("CarteActivity", "La carte n'est pas initialisée (mMap est null)")
                }
            } else {
                Log.e("CarteActivity", "Impossible de récupérer la position actuelle de l'utilisateur.")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val now = System.currentTimeMillis()
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()) - SensorManager.GRAVITY_EARTH

            if (acceleration > shakeThreshold) {
                if (now - lastShakeTime >= 1000) { // Empêche plusieurs déclenchements successifs
                    lastShakeTime = now
                    launchListedActivities()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ne rien faire ici
    }

    private fun launchListedActivities() {
        val intent = Intent(this, ListedActivitiesActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 100
    }
}