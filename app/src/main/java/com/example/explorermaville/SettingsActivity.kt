package com.example.explorermaville

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.explorermaville.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Initialisation des préférences partagées
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Récupérer la valeur actuelle du rayon de recherche
        val currentRadius = sharedPreferences.getInt("radius", 1000)

        // Afficher la valeur actuelle dans l'EditText
        binding.radiusEditText.setText(currentRadius.toString())

        // Définir un écouteur sur le bouton "Enregistrer"
        binding.saveButton.setOnClickListener {
            // Récupérer la valeur saisie dans l'EditText
            val newRadius = binding.radiusEditText.text.toString().toInt()

            // Enregistrer la nouvelle valeur du rayon de recherche dans les préférences partagées
            val editor = sharedPreferences.edit()
            editor.putInt("radius", newRadius)
            editor.apply()
            val resultIntent = Intent()
            resultIntent.putExtra("newRadius", newRadius)
            setResult(Activity.RESULT_OK, resultIntent)

            // Afficher un message pour indiquer que les paramètres ont été enregistrés
            Toast.makeText(this, "Paramètres enregistrés avec succès", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}