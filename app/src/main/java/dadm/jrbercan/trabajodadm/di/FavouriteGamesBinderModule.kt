package dadm.jrbercan.trabajodadm.di

import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesDataSource
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesDataSourceImpl
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepository
import dadm.jrbercan.trabajodadm.data.favouritesGames.FavouriteGamesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteGamesBinderModule {

    @Binds
    abstract fun bindFavouritesGamesDataSource(favouritesDataSourceImpl: FavouriteGamesDataSourceImpl) : FavouriteGamesDataSource

    @Binds
    abstract fun bindFavouritesGamesRepository(favouritesRepositoryImpl: FavouriteGamesRepositoryImpl) : FavouriteGamesRepository
}