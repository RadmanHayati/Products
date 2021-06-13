package ir.alizeyn.products.data.di.product

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.products.data.network.mapper.Mapper
import ir.alizeyn.products.data.network.product.model.NetworkProduct
import ir.alizeyn.products.data.network.product.repo.ProductMapper
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.data.network.product.repo.ProductsRepositoryImpl
import ir.alizeyn.products.domain.product.model.Product
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  ProductModule {

    @Binds
    abstract fun productMapper(mapper: ProductMapper): Mapper<NetworkProduct, Product>

    @Binds
    @Singleton
    abstract fun repository(repository: ProductsRepositoryImpl): ProductsRepository
}