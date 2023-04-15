package dadm.jrbercan.trabajodadm.data.saleGames

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dadm.jrbercan.trabajodadm.data.saleGames.model.SaleGameDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object SaleGamesApiService {
    private const val BASE_URL =
        "https://www.cheapshark.com/api/1.0/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    interface SaleGamesApiService {

        @GET("deals?format=json")
        suspend fun getAllSaleGames(): List<SaleGameDto>
    }

    object SaleGamesApi {
        val retrofitService: SaleGamesApiService by lazy { retrofit.create(SaleGamesApiService::class.java) }
    }

}