package com.example.explorermaville.model.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.explorermaville.model.ActiviteModel


class CarteViewModel : ViewModel() {
    // Vérifiez si les marqueurs sont déjà chargés, sinon appelez la méthode pour les préparer à partir des données des activités
    // Propriété LiveData pour observer les marqueurs à afficher sur la carte
    var marqueursLiveData: MutableLiveData<List<ActiviteModel>>? = null
        get() {
            // Vérifiez si les marqueurs sont déjà chargés, sinon appelez la méthode pour les préparer à partir des données des activités
            if (field == null) {
                field = MutableLiveData<List<ActiviteModel>>()
                preparerMarqueursDepuisDonneesDesActivites()
            }
            return field
        }
        private set

    private fun preparerMarqueursDepuisDonneesDesActivites() {
        // Ici, vous pouvez utiliser les données des activités (listeActivitesLiveData) pour préparer les marqueurs à afficher sur la carte
        // Par exemple, créez une liste de MarkerModel en parcourant la liste des activités et en créant un marqueur pour chaque activité
        // Une fois les marqueurs préparés, mettez à jour la propriété marqueursLiveData avec la nouvelle liste de marqueurs
        // Exemple : marqueursLiveData.setValue(listeDesMarqueursPrepares);
    }
}