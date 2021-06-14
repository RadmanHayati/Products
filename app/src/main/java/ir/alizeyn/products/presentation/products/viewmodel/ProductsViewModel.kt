package ir.alizeyn.products.presentation.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.core.state.StateMutableLiveData
import ir.alizeyn.products.data.network.mapper.Mapper
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val mapper: Mapper<Product, ProductUiModel>
) : ViewModel() {

    private val _products = StateMutableLiveData<List<ProductUiModel>>()

    val products: LiveData<StateData<List<ProductUiModel>>>
        get() = _products


    fun getProducts() = viewModelScope.launch {

        repository.getProducts()
            .onStart { _products.setLoading() }
            .catch { _products.setError(it) }
            .map { it.map { product -> mapper.map(product) } }
            .collect { _products.setSuccess(it) }
    }
}