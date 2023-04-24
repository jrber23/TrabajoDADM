package dadm.jrbercan.trabajodadm.di

import android.content.Context
import android.net.ConnectivityManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoDataSourceImpl
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.SteamGameDtoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MoreGameInfoProviderModule {
    /*@Provides
    @Singleton
    fun  provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }*/

    @Named("Steam")
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://store.steampowered.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                .add(SteamGameDtoAdapter())
                .build()))
            .build()
    }
}