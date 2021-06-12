package ir.alizeyn.products.data.network.product.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class NetworkProduct(
    val id: String,
    val title: String,
    @SerializedName("imageURL")
    val imageUrl: String,
    val price: BigDecimal,
    val strikePrice: BigDecimal?,
    val description: String
)