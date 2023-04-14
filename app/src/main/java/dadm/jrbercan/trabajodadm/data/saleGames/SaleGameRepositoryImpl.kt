package dadm.jrbercan.trabajodadm.data.saleGames

import dadm.jrbercan.trabajodadm.data.saleGames.model.toDomain
import dadm.jrbercan.trabajodadm.domain.model.Game
import dadm.jrbercan.trabajodadm.utils.NoInternetException
import javax.inject.Inject

class SaleGameRepositoryImpl @Inject constructor(
    private val saleGameDataSource: SaleGameDataSource,
    private val connectivityChecker: ConnectivityChecker
) :
    SaleGameRepository {
    override suspend fun getAllSaleGames(): Result<ArrayList<Game>> {
        TODO("Not yet implemented")
    }
}