package dadm.jrbercan.trabajodadm.data.moreGameInfo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDealsDto(
    val deals: List<Deals>
)

@JsonClass(generateAdapter = true)
data class Deals(
    val storeID: String,
    val price: String,
    val retailPrice: String,
    val savings: String
)
