package dadm.jrbercan.trabajodadm.data.saleGames

import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class SaleGameDataSourceImpl @Inject constructor(retrofit: Retrofit) : SaleGameDataSource {

    interface SaleGameRetrofit {
        @GET("api/1.0/deals")
        suspend fun getAllSaleGames(): Response<ArrayList<SaleGameDto>>
    }

    private val retrofitSaleGameService = retrofit.create(SaleGameRetrofit::class.java)

    override suspend fun getAllSaleGames(): Response<ArrayList<SaleGameDto>> {
        return try {
            retrofitSaleGameService.getAllSaleGames()
        } catch (e: java.lang.Exception) {
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }


}