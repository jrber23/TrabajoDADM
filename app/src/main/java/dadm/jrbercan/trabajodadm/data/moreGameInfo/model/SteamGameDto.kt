package dadm.jrbercan.trabajodadm.data.moreGameInfo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SteamGameDto(
    val data: IdSteam
)

@JsonClass(generateAdapter = true)
data class IdSteam(
    val data: Data?=null
)

@JsonClass(generateAdapter = true)
data class Data(
    val name: String,
    val detailed_description: String,
    val short_description: String,
    val header_image: String
    )