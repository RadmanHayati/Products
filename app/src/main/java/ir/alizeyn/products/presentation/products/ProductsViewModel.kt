package ir.alizeyn.products.presentation.products

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.products.data.network.product.repo.ProductsRepository
import ir.alizeyn.products.domain.product.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel(), LifecycleObserver {

    //todo fix visibility of livedata
    val products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        getProducts()
    }

    public fun getProducts() = viewModelScope.launch {
        repository.getProducts()
            .collect {
                products.value = it
            }
    }
}