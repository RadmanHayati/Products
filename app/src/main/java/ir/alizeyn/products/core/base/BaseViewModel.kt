package ir.alizeyn.products.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
}