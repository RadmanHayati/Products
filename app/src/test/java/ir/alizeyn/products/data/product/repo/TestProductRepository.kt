package ir.alizeyn.products.data.product.repo

import com.google.common.truth.Truth
import ir.alizeyn.products.data.network.api.ProductsApiService
import ir.alizeyn.products.data.network.product.model.NetworkProduct
import ir.alizeyn.products.data.network.product.model.ProductResponse
import ir.alizeyn.products.data.network.product.repo.ProductMapper
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.data.network.product.repo.ProductsRepositoryImpl
import ir.alizeyn.products.domain.product.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TestProductRepository {

    @Mock
    private lateinit var api: ProductsApiService
    @Mock
    private lateinit var mapper: ProductMapper

    private val repository: ProductsRepository by lazy {
        ProductsRepositoryImpl(api, mapper)
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getProductsSuccessfully() = runBlocking {

        val networkProduct =
            NetworkProduct(
                "0a",
                "Tomato",
                "imageUrl",
                BigDecimal.valueOf(1.31),
                null,
                "description"
            )

        val product = Product(
            "0a",
            "Tomato",
            "imageUrl",
            BigDecimal.valueOf(1.31),
            null,
            "description"
        )

        `when`(mapper.map(networkProduct)).thenReturn(product)
        `when`(api.getProducts()).thenReturn(ProductResponse(listOf(networkProduct)))

        repository.getProducts().collect {
            Truth.assertThat(it).isEqualTo(listOf(product))
        }
    }

    @Test
    fun `Failed get products`() = runBlocking {
        val msg = "Network Error"
        `when`(api.getProducts()).thenThrow(RuntimeException(msg))
        try {
            val flow = repository.getProducts()
        } catch (e: Exception) {
            Truth.assertThat(e).hasMessageThat().isEqualTo(msg)
        }
    }
}