package dadm.jrbercan.trabajodadm.data.saleGames.model

import com.squareup.moshi.JsonClass

// @JsonClass(generateAdapter = true)
data class SaleGameDto(
    val dealID: String,
    val title: String,
    val salePrice: String,
    val thumb: String
)