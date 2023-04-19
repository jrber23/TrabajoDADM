package dadm.jrbercan.trabajodadm.data.moreGameInfo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopsDto(
    val storeID: String,
    val storeName: String,
    val isActive: Int,
    val images: Images
    )

@JsonClass(generateAdapter = true)
data class Images(
    val logo: String
)
