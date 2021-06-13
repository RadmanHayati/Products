package ir.alizeyn.products.presentation.products.mapper

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import ir.alizeyn.products.data.network.common.Mapper
import ir.alizeyn.products.domain.product.model.Product
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import java.math.BigDecimal
import javax.inject.Inject

class ProductUiMapper @Inject constructor() : Mapper<Product, ProductUiModel> {

    override fun map(input: Product): ProductUiModel {

        val prePrice = "statt "
        val strikePrice: SpannableString? = input.strikePrice?.let {
            val formattedStrikePrice = "$prePrice${getCurrencyFormattedPrice(it)}"
            SpannableString(formattedStrikePrice)
        }?.apply {
            setSpan(
                StrikethroughSpan(),
                prePrice.length,
                length - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return ProductUiModel(
            input.id,
            input.title,
            input.imageUrl,
            getCurrencyFormattedPrice(input.price),
            strikePrice,
            input.description
        )
    }

    private fun getCurrencyFormattedPrice(price: BigDecimal): String {
        return "$price €".replace(".", ",")
    }
}