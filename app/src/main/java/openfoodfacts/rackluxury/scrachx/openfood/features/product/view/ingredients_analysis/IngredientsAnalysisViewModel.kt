package openfoodfacts.rackluxury.scrachx.openfood.features.product.view.ingredients_analysis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import openfoodfacts.rackluxury.scrachx.openfood.models.Product
import openfoodfacts.rackluxury.scrachx.openfood.network.OpenFoodAPIClient
import javax.inject.Inject

@HiltViewModel
class IngredientsAnalysisViewModel @Inject constructor(
    private val api: OpenFoodAPIClient
) : ViewModel() {

    val product = MutableLiveData<Product>()

    val ingredients = product.switchMap { product ->
        liveData(Dispatchers.IO) {
            try {
                emit(api.getIngredients(product))
            } catch (err: Exception) {
                emit(null)
            }
        }
    }
}