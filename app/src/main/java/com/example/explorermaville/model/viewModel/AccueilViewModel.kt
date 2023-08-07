package com.example.explorermaville.model.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.explorermaville.model.ActiviteModel


class AccueilViewModel : ViewModel() {
    // Vérifiez si la liste des activités est déjà chargée, sinon appelez la méthode pour récupérer les données depuis l'API
    // Propriété LiveData pour observer les données des activités
    var listeActivitesLiveData: MutableLiveData<List<ActiviteModel>>? = null
        get() {
            // Vérifiez si la liste des activités est déjà chargée, sinon appelez la méthode pour récupérer les données depuis l'API
            if (field == null) {
                field = MutableLiveData()
                chargerListeActivitesDepuisAPI()
            }
            return field
        }
        private set

    private fun chargerListeActivitesDepuisAPI() {
        // Ici, vous pouvez utiliser Retrofit ou toute autre méthode pour appeler l'API et récupérer les données des activités
        // Une fois les données reçues, mettez à jour la propriété listeActivitesLiveData avec les nouvelles données
        // Exemple : listeActivitesLiveData.setValue(listeDesActivitesRecuperees);
    }
}