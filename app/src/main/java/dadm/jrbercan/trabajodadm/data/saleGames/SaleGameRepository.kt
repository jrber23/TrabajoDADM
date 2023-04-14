package dadm.jrbercan.trabajodadm.data.saleGames

import dadm.jrbercan.trabajodadm.domain.model.Game

interface SaleGameRepository {

    suspend fun getAllSaleGames() : Result<ArrayList<Game>>

}