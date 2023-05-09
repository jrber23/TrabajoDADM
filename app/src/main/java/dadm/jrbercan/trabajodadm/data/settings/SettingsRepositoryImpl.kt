package dadm.jrbercan.trabajodadm.data.settings


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImp @Inject constructor(private val settingsDataSource: SettingsDataSource) : SettingsRepository {

    override fun getEmail(): Flow<String> {
        return settingsDataSource.getEmail()
    }

    override fun getPrice(): Flow<String> {
        return settingsDataSource.getPrice()
    }
}