package dadm.jrbercan.trabajodadm.di

import dadm.jrbercan.trabajodadm.data.settings.SettingsDataSource
import dadm.jrbercan.trabajodadm.data.settings.SettingsDataSourceImp
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepository
import dadm.jrbercan.trabajodadm.data.settings.SettingsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {

    @Binds
    abstract fun bindSettingsDataSource(settingsDataSourceImp: SettingsDataSourceImp) : SettingsDataSource
    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImp: SettingsRepositoryImp) : SettingsRepository
}