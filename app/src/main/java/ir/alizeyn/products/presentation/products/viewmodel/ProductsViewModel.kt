package ir.alizeyn.products.presentation.products.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.products.core.base.BaseViewModel
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.core.state.StateMutableLiveData
import ir.alizeyn.products.data.network.mapper.Mapper
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val mapper: Mapper<Product, ProductUiModel>
) : BaseViewModel() {

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _products.setError(throwable)
    }

    private val _products = StateMutableLiveData<List<ProductUiModel>>()

    val products: LiveData<StateData<List<ProductUiModel>>>
        get() = _products

    init {
        getProducts()
    }

    fun getProducts() = launch {

        _products.setLoading()
        repository.getProducts()
            .map {
                it.map { product -> mapper.map(product) }
            }
            .collect {
                _products.setSuccess(it)
            }
    }
}