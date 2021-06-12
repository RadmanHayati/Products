package ir.alizeyn.products.presentation.products

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.products.core.base.BaseViewModel
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.core.state.StateMutableLiveData
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.domain.product.model.Product
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : BaseViewModel() {

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _products.setError(throwable)
    }

    private val _products = StateMutableLiveData<List<Product>>()

    val products: LiveData<StateData<List<Product>>>
        get() = _products

    init {
        getProducts()
    }

    fun getProducts() = launch {
        _products.setLoading()
        repository.getProducts()
            .collect {
                _products.setSuccess(it)
            }
    }
}