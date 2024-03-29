package openfoodfacts.rackluxury.scrachx.openfood.category.network

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Abdelali Eramli on 27/12/2017.
 */
interface CategoryNetworkService {
    @GET("categories.json")
    fun getCategories(): Single<CategoryResponse>
}