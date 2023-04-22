package dadm.jrbercan.trabajodadm.data.moreGameInfo

import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.GameDealsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.ShopsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.SteamGameDto
import retrofit2.Response
import retrofit2.http.Query

interface MoreGameInfoDataSource {
    suspend fun getAllShops(): Response<List<ShopsDto>>
    suspend fun getSteamInfo(number: String, language: String): Response<SteamGameDto>
    suspend fun getGameDeals(number: String): Response<GameDealsDto>
}