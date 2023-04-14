package dadm.jrbercan.trabajodadm.data.saleGames

import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import retrofit2.Response

interface SaleGameDataSource {

    suspend fun getAllSaleGames(): Response<ArrayList<SaleGameDto>>

}