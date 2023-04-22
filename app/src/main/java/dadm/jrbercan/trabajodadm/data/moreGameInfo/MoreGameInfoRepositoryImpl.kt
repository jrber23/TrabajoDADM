package dadm.jrbercan.trabajodadm.data.moreGameInfo

import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.GameDealsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.ShopsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.SteamGameDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.toDomain
import dadm.jrbercan.trabajodadm.utils.NoInternetException
import javax.inject.Inject

class MoreGameInfoRepositoryImpl @Inject constructor(private val retrofitDataSource: MoreGameInfoDataSource, private val connectivityChecker : ConnectivityChecker): MoreGameInfoRepository{
    override suspend fun getAllShops(): Result<List<ShopsDto>> {
        if (connectivityChecker.isConnectionAvailable()) return retrofitDataSource.getAllShops().toDomain()
        else return Result.failure(NoInternetException())
    }

    override suspend fun getSteamInfo(number: String, language: String): Result<SteamGameDto> {
        if (connectivityChecker.isConnectionAvailable()) return retrofitDataSource.getSteamInfo(number,language).toDomain()
        else return Result.failure(NoInternetException())
    }

    override suspend fun getGameDeals(number: String): Result<GameDealsDto> {
        if (connectivityChecker.isConnectionAvailable()) return retrofitDataSource.getGameDeals(number).toDomain()
        else return Result.failure(NoInternetException())
    }
}