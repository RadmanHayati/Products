package ir.alizeyn.products.domain.product.model

import java.math.BigDecimal

data class Product(
    var id: String,
    var title: String,
    var imageUrl: String,
    var price: BigDecimal,
    var strikePrice: BigDecimal?,
    var description: String
)