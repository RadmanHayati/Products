package ir.alizeyn.products.data.network.product.repo

import ir.alizeyn.products.domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<List<Product>>
}