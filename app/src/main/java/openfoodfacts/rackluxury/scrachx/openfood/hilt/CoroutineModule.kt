package openfoodfacts.rackluxury.scrachx.openfood.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import openfoodfacts.rackluxury.scrachx.openfood.utils.CoroutineDispatchers
import openfoodfacts.rackluxury.scrachx.openfood.utils.CoroutineDispatchersImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Binds
    @Singleton
    abstract fun bindCoroutineDispatchers(coroutineDispatchersImpl: CoroutineDispatchersImpl): CoroutineDispatchers
}
