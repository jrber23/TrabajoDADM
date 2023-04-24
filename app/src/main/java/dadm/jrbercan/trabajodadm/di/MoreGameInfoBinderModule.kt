package dadm.jrbercan.trabajodadm.di

import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoDataSource
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoDataSourceImpl
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoRepository
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.MoreGameInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MoreGameInfoBinderModule {
    @Binds
    abstract fun bindMoreGameInfoRepository(moreGameInfoRepositoryImpl: MoreGameInfoRepositoryImpl): MoreGameInfoRepository
    @Binds
    abstract fun bindMoreGameInfoDataSource(moreGameInfoDataSourceImpl: MoreGameInfoDataSourceImpl): MoreGameInfoDataSource
}