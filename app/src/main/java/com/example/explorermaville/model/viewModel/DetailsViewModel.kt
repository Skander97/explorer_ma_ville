import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.explorermaville.model.ActiviteModel

class DetailsViewModel : ViewModel() {
    // Ici, vous pouvez mettre à jour activiteDetailsLiveData lorsque l'utilisateur sélectionne une activité dans la vue d'accueil ou la vue de carte
    // Par exemple, utilisez un Listener pour écouter la sélection de l'utilisateur et mettez à jour activiteDetailsLiveData avec les informations complètes de l'activité sélectionnée
    // Propriété LiveData pour observer les informations complètes d'une activité sélectionnée
    val activiteDetailsLiveData: MutableLiveData<ActiviteModel>? = null
}