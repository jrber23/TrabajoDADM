package dadm.jrbercan.trabajodadm.di

import dadm.jrbercan.trabajodadm.data.moreGameInfo.MoreGameInfoDataSource
import dadm.jrbercan.trabajodadm.data.moreGameInfo.MoreGameInfoDataSourceImpl
import dadm.jrbercan.trabajodadm.data.moreGameInfo.MoreGameInfoRepository
import dadm.jrbercan.trabajodadm.data.moreGameInfo.MoreGameInfoRepositoryImpl
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