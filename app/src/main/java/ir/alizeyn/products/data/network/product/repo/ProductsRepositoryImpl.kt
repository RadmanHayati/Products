package ir.alizeyn.products.data.network.product.repo

import ir.alizeyn.products.data.network.api.ProductsApiService
import ir.alizeyn.products.data.network.mapper.Mapper
import ir.alizeyn.products.data.network.product.model.NetworkProduct
import ir.alizeyn.products.domain.product.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: ProductsApiService,
    private val mapper: Mapper<NetworkProduct, Product>
) : ProductsRepository {

    override fun getProducts() = flow {
        val response = api.getProducts()
        emit(mapProducts(response.products))
    }.flowOn(Dispatchers.IO)

    private fun mapProducts(networkProducts: List<NetworkProduct>): List<Product> =
        networkProducts.map {
            mapper.map(it)
        }
}