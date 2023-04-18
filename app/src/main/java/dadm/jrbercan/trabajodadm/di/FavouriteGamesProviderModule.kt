package dadm.jrbercan.trabajodadm.di

import android.content.Context
import androidx.room.Room
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesContact.DB_NAME
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesDao
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouritesGamesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouriteGamesProviderModule {

    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesGamesDatabase =
        Room.databaseBuilder(context, FavouritesGamesDatabase::class.java, DB_NAME).build()

    @Provides
    fun provideFavouritesDao(favouritesGamesDatabase: FavouritesGamesDatabase): FavouriteGamesDao =
        favouritesGamesDatabase.favouritesGamesDao()

}