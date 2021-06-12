package ir.alizeyn.products.data.network.product.repo

import ir.alizeyn.products.data.network.common.Mapper
import ir.alizeyn.products.data.network.product.model.NetworkProduct
import ir.alizeyn.products.domain.product.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : Mapper<NetworkProduct, Product> {

    override fun map(input: NetworkProduct): Product =
        Product(
            input.id,
            input.title,
            input.imageUrl,
            input.price,
            input.strikePrice,
            input.description
        )
}