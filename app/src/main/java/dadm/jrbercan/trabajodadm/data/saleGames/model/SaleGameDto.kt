package dadm.jrbercan.trabajodadm.data.saleGames.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SaleGameDto(
    val dealId: String,
    val title: String,
    val salePrice: String
)