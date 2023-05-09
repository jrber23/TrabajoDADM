package dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo

import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.GameDealsDto
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.ShopsDto
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.SteamGameDto
import retrofit2.Response
import retrofit2.http.Query

interface MoreGameInfoRepository {
    suspend fun getAllShops(): Result<List<ShopsDto>>
    suspend fun getSteamInfo(number: String, language: String): Result<SteamGameDto>
    suspend fun getGameDeals(number: String): Result<GameDealsDto>
}