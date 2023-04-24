package dadm.jrbercan.trabajodadm.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getEmail() : Flow<String>
    fun getPrice() : Flow<Int>
}