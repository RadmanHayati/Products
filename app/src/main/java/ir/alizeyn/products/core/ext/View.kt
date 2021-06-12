package ir.alizeyn.products.core.ext

import android.graphics.Paint
import android.view.View
import android.widget.TextView

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

fun TextView.strike() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}