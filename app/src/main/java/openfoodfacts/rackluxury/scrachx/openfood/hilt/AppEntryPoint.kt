package openfoodfacts.rackluxury.scrachx.openfood.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import openfoodfacts.rackluxury.scrachx.openfood.utils.LocaleManager

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPoint {

    fun localeManager(): LocaleManager
}
