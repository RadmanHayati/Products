package ir.alizeyn.products.presentation.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.products.data.network.mapper.Mapper
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.mapper.ProductUiMapper
import ir.alizeyn.products.presentation.products.model.ProductUiModel

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductUiModule {

    @Binds
    abstract fun productMapper(mapper: ProductUiMapper): Mapper<Product, ProductUiModel>
}


