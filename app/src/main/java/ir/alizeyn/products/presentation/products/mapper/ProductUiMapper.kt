package ir.alizeyn.products.presentation.products.mapper

import ir.alizeyn.products.data.network.common.Mapper
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import java.math.BigDecimal
import javax.inject.Inject

class ProductUiMapper @Inject constructor() : Mapper<Product, ProductUiModel> {

    override fun map(input: Product): ProductUiModel {

        return ProductUiModel(
            input.id,
            input.title,
            input.imageUrl,
            formatPrice(input.price),
            input.strikePrice?.let {
                formatPrice(it)
            },
            input.description
        )
    }

    private fun formatPrice(price: BigDecimal): String {
        return "$price €".replace(".", ",")
    }
}