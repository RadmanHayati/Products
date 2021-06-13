package ir.alizeyn.products.presentation.products.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUiModel(
    var id: String,
    var title: String,
    var imageUrl: String,
    var price: String,
    var strikePrice: String?,
    var description: String
): Parcelable