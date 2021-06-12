package ir.alizeyn.products.data.network.api

import ir.alizeyn.products.data.network.product.model.ProductResponse
import retrofit2.http.GET

interface ProductsApiService {

    @GET("73402840-975a-4f7c-9964-f865d4356d0c/products_simple.json")
    suspend fun getProducts(): ProductResponse
}