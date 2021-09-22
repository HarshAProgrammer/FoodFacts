package openfoodfacts.rackluxury.scrachx.openfood.models.entities

interface EntityResponse<T> {
    fun map(): T
}
