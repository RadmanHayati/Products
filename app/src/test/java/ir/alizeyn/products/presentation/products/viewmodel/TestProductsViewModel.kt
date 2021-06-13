package ir.alizeyn.products.presentation.products.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.mapper.ProductUiMapper
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import ir.alizeyn.products.utils.CoroutineTestRule
import ir.alizeyn.products.utils.observeLimit
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TestProductsViewModel {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductsViewModel

    @Mock
    private lateinit var mapper: ProductUiMapper

    @Mock
    private lateinit var repository: ProductsRepository

    private val product = Product(
        "", "", "", BigDecimal.valueOf(1.12), null, ""
    )
    private val uiProduct = ProductUiModel(
        "", "", "", "1,12 €", null, ""
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(mapper.map(product)).thenReturn(uiProduct)
        `when`(repository.getProducts()).thenReturn(flowOf(listOf()))
        viewModel = ProductsViewModel(repository, mapper)
    }

    @Test
    fun loadProductsWithSuccess() = runBlocking {

        `when`(repository.getProducts()).thenReturn(flowOf(listOf(product)))
        viewModel.getProducts()
        viewModel.products.observeLimit({
            Truth.assertThat(it is StateData.Success).isTrue()
        })
    }

    @Test
    fun loadProductsWithError() = runBlocking {

        val msg = "Network Error"
        `when`(repository.getProducts()).thenThrow(RuntimeException(msg))
        viewModel.getProducts()
        viewModel.products.observeLimit({
            Truth.assertThat(it is StateData.Error).isTrue()
        })
    }
}