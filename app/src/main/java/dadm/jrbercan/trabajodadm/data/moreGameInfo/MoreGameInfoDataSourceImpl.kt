package dadm.jrbercan.trabajodadm.data.moreGameInfo

import android.util.Log
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.GameDealsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.ShopsDto
import dadm.jrbercan.trabajodadm.data.moreGameInfo.model.SteamGameDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap
import javax.inject.Inject
import javax.inject.Named

class MoreGameInfoDataSourceImpl @Inject constructor(@Named("CheapShark") private val retrofit: Retrofit, @Named("Steam") private val retrofit2: Retrofit): MoreGameInfoDataSource{

    private val retrofitShopService = retrofit.create(MoreGameInfoShopsRetrofit::class.java)
    private val retrofitSteamServiceSpanish = retrofit2.create(MoreGameInfoSteamRetrofitSpanish::class.java)
    private val retrofitSteamServiceEnglish = retrofit2.create(MoreGameInfoSteamRetrofitEnglish::class.java)
    private val retrofitGameDealsService = retrofit.create(MoreGameInfoGameDeals::class.java)

    override suspend fun getAllShops(): Response<List<ShopsDto>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                retrofitShopService.getAllShops()
            } catch (e: Exception) {
                Response.error(400, ResponseBody.create(MediaType.parse("text/plain"),e.toString()))
            }
        }

    override suspend fun getSteamInfo(number: String, language: String): Response<SteamGameDto> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val queries: MutableMap<String, String> = mutableMapOf()
                queries["language"] = language
                queries["appids"] = number
                if (language == "es") { retrofitSteamServiceSpanish.getSteamInfo(queries) }
                else { retrofitSteamServiceEnglish.getSteamInfo(queries) }
            } catch (e: Exception) {
                Response.error(400, ResponseBody.create(MediaType.parse("text/plain"),e.toString()))
            }
        }

    override suspend fun getGameDeals(number: String): Response<GameDealsDto> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                retrofitGameDealsService.getGameDeals(number)
            } catch (e: Exception) {
                Response.error(400, ResponseBody.create(MediaType.parse("text/plain"),e.toString()))
            }
        }

    interface MoreGameInfoShopsRetrofit {
        @GET("api/1.0/stores")
        suspend fun getAllShops(): Response<List<ShopsDto>>
    }

    interface MoreGameInfoSteamRetrofitEnglish {
        @GET("appdetails")
        @Headers("Accept-Language: en;q=0.5")
        suspend fun getSteamInfo(@QueryMap queries: MutableMap<String, String>): Response<SteamGameDto>
    }

    interface MoreGameInfoSteamRetrofitSpanish {
        @GET("appdetails")
        @Headers("Accept-Language: es-ES,es;q=0.5")
        suspend fun getSteamInfo(@QueryMap queries: MutableMap<String, String>): Response<SteamGameDto>
    }

    interface MoreGameInfoGameDeals {
        @GET("api/1.0/games")
        suspend fun getGameDeals(@Query("id") number: String): Response<GameDealsDto>
    }
}