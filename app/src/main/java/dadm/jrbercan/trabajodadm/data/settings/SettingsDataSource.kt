package dadm.jrbercan.trabajodadm.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {

    fun getEmail() : Flow<String>
    fun getPrice() : Flow<Int>
}