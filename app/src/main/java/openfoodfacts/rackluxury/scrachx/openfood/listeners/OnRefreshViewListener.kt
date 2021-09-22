package openfoodfacts.rackluxury.scrachx.openfood.listeners

import openfoodfacts.rackluxury.scrachx.openfood.models.ProductState

/**
 * Created by Lobster on 19.04.18.
 */
fun interface OnRefreshViewListener {
    fun refreshView(productState: ProductState)
}