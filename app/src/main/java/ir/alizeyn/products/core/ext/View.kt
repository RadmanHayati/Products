package ir.alizeyn.products.core.ext

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.goneIf(hide: Boolean) {
    if (hide) {
        gone()
    } else {
        visible()
    }
}

fun View.hideIf(hide: Boolean) {
    if (hide) {
        invisible()
    } else {
        visible()
    }
}