package com.example.explorermaville

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.explorermaville.databinding.ActivityListedActivitesBinding
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListedActivitiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListedActivitesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LieuxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListedActivitesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LieuxAdapter()
        recyclerView.adapter = adapter

        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val latLng: LatLng? = intent.getParcelableExtra("latLng")
        Log.wtf("wtf", "Latitude à passer à l'intent : $latitude")
        Log.wtf("wtf", "Latitude à passer à l'intent : $longitude")
        Log.wtf("wtf", "LatLng à passer à l'intent : $latLng")



        // Appel à l'API Foursquare pour récupérer les lieux proches
        val call = RetrofitClient.foursquareService.getNearbyPlaces(
            latLng = "$latitude,$longitude",
            radius = 1000 // rayon de recherche en mètres
        )

        call.enqueue(object : Callback<FoursquareResponse> {
            override fun onResponse(
                call: Call<FoursquareResponse>,
                response: Response<FoursquareResponse>
            ) {
                // Traitement de la réponse ici
                if (response.isSuccessful) {
                    val foursquareResponse = response.body()
                    val venues = foursquareResponse?.results
                    Log.wtf("wtf", "foursquare : ${response.body()?.results}")


                    // Vérifier si la liste des lieux n'est pas vide
                    if (!venues.isNullOrEmpty()) {
                        // Mettre à jour les données de l'adaptateur avec la liste des lieux récupérés
                        adapter.setData(venues)

                        // Notifier l'adaptateur que les données ont changé
                        adapter.notifyDataSetChanged()

                        // Afficher un message si des lieux ont été trouvés
                        Toast.makeText(
                            this@ListedActivitiesActivity,
                            "Lieux trouvés : ${venues.size}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Afficher un message si aucun lieu n'a été trouvé
                        Toast.makeText(
                            this@ListedActivitiesActivity,
                            "Aucun lieu trouvé à proximité",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Gérer les réponses d'erreur ici
                    Toast.makeText(
                        this@ListedActivitiesActivity,
                        "Erreur lors de la récupération des lieux",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<FoursquareResponse>, t: Throwable) {
                // Gérer les échecs de l'appel ici
                Toast.makeText(
                    this@ListedActivitiesActivity,
                    "Erreur : ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}