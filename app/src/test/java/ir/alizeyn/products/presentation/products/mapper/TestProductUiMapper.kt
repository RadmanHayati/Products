package ir.alizeyn.products.presentation.products.mapper

import com.google.common.truth.Truth
import ir.alizeyn.products.domain.product.model.Product
import org.junit.Test
import java.math.BigDecimal

class TestProductUiMapper {

    private val product = Product(
        "", "", "", BigDecimal.valueOf(2.79), null, ""
    )
    private val mapper = ProductUiMapper()

    @Test
    fun testPriceFormat() {
        val uiModelProduct = mapper.map(product)
        Truth.assertThat(uiModelProduct.price).isEqualTo("2,79 €")
    }
}