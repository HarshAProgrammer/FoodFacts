package openfoodfacts.rackluxury.scrachx.openfood.features.compare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import openfoodfacts.rackluxury.scrachx.openfood.analytics.AnalyticsEvent
import openfoodfacts.rackluxury.scrachx.openfood.analytics.MatomoAnalytics
import openfoodfacts.rackluxury.scrachx.openfood.models.Product
import openfoodfacts.rackluxury.scrachx.openfood.models.entities.additive.AdditiveName
import openfoodfacts.rackluxury.scrachx.openfood.repositories.ProductRepository
import openfoodfacts.rackluxury.scrachx.openfood.utils.CoroutineDispatchersTest
import openfoodfacts.rackluxury.scrachx.openfood.utils.LocaleManager
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class ProductCompareViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val productRepository: ProductRepository = mock()
    private val localeManager: LocaleManager = mock()
    private val matomoAnalytics: MatomoAnalytics = mock()
    private val coroutineDispatcher = CoroutineDispatchersTest()

    private lateinit var viewModel: ProductCompareViewModel

    @Before
    fun setup() {
        whenever(localeManager.getLanguage()).doReturn("en")
        viewModel = ProductCompareViewModel(productRepository, localeManager, matomoAnalytics, coroutineDispatcher)
    }

    @Test
    fun onInit_productsAreEmpty() {
        assertThat(viewModel.productsFlow.value).isEmpty()
    }

    @Test
    fun addProductToCompare_shouldEmitAlreadyExistEvent_whenTheSameProductAdded() = runBlockingTest {
        // GIVEN
        val product: Product = mock {
            on { code } doReturn "qwerty"
        }
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty", "en"))
            .doReturn(Single.just(AdditiveName("test-name")))

        val flowItems = mutableListOf<Unit>()
        val job = launch {
            viewModel.alreadyExistFlow.toList(flowItems)
        }

        // WHEN
        viewModel.addProductToCompare(product)
        viewModel.addProductToCompare(product)

        // THEN
        assertThat(flowItems.size).isEqualTo(1)
        job.cancel()
    }

    @Test
    fun addProductToCompare_shouldAddProducts_whenProductsAreDifferent() = runBlockingTest {
        // GIVEN
        val product1: Product = mock {
            on { code } doReturn "qwerty1"
        }
        val product2: Product = mock {
            on { code } doReturn "qwerty2"
        }
        val product3: Product = mock {
            on { code } doReturn "qwerty3"
        }
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty1", "en"))
            .doReturn(Single.just(AdditiveName("test-name1")))
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty2", "en"))
            .doReturn(Single.just(AdditiveName("test-name2")))
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty3", "en"))
            .doReturn(Single.just(AdditiveName("test-name3")))

        val flowItems = mutableListOf<List<ProductCompareViewModel.CompareProduct>>()
        val job = launch {
            viewModel.productsFlow.toList(flowItems)
        }

        // WHEN
        viewModel.addProductToCompare(product1)
        viewModel.addProductToCompare(product2)
        viewModel.addProductToCompare(product3)

        // THEN
        assertThat(flowItems.last().size).isEqualTo(3)
        assertThat(flowItems.last()[0].product.code).isEqualTo("qwerty1")
        assertThat(flowItems.last()[1].product.code).isEqualTo("qwerty2")
        assertThat(flowItems.last()[2].product.code).isEqualTo("qwerty3")
        job.cancel()
    }

    @Test
    fun addProductToCompare_shouldTrackRightAnalytics_whenProductsAddedToCompare() = runBlockingTest {
        // GIVEN
        val product1: Product = mock {
            on { code } doReturn "qwerty1"
        }
        val product2: Product = mock {
            on { code } doReturn "qwerty2"
        }
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty1", "en"))
            .doReturn(Single.just(AdditiveName("test-name1")))
        whenever(productRepository.getAdditiveByTagAndLanguageCode("qwerty2", "en"))
            .doReturn(Single.just(AdditiveName("test-name2")))

        // WHEN
        viewModel.addProductToCompare(product1)
        viewModel.addProductToCompare(product2)

        // THEN
        val argumentCapture = argumentCaptor<AnalyticsEvent>()
        verify(matomoAnalytics, times(3)).trackEvent(argumentCapture.capture())
        assertThat((argumentCapture.allValues[0] as AnalyticsEvent.AddProductToComparison).barcode).isEqualTo("qwerty1")
        assertThat((argumentCapture.allValues[1] as AnalyticsEvent.AddProductToComparison).barcode).isEqualTo("qwerty2")
        assertThat((argumentCapture.allValues[2] as AnalyticsEvent.CompareProducts).count).isEqualTo(2f)
    }
}
