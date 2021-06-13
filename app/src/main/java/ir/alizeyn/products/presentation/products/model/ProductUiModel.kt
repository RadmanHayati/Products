package ir.alizeyn.products.presentation.products.model

import android.text.SpannableString

data class ProductUiModel(
    var id: String,
    var title: String,
    var imageUrl: String,
    var price: String,
    var strikePrice: SpannableString?,
    var description: String
)