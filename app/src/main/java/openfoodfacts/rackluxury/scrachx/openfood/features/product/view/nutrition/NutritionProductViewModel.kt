package openfoodfacts.rackluxury.scrachx.openfood.features.product.view.nutrition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import openfoodfacts.rackluxury.scrachx.openfood.models.ProductState

class NutritionProductViewModel : ViewModel() {
    val productState = MutableLiveData<ProductState>()
}