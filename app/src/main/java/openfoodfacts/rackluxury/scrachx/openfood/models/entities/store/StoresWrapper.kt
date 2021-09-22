package openfoodfacts.rackluxury.scrachx.openfood.models.entities.store

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import openfoodfacts.rackluxury.scrachx.openfood.models.entities.EntityResponse
import openfoodfacts.rackluxury.scrachx.openfood.models.entities.EntityWrapper

@JsonDeserialize(using = StoresWrapperDeserializer::class)
class StoresWrapper(stores: List<EntityResponse<Store>>) : EntityWrapper<Store>(stores)