package dadm.jrbercan.trabajodadm.data.settings

import android.content.SharedPreferences
import dadm.jrbercan.trabajodadm.data.settings.SettingsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsDataSourceImp @Inject constructor(private val sharedPreferences: SharedPreferences) : SettingsDataSource {

    private val emailKey : String = getEmailPreference()
    private val priceKey : String = "price"

    private val defaultPrice: Int = 15

    private fun getEmailPreference() : String = sharedPreferences.getString(emailKey, "") ?: ""

    override fun getEmail(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (emailKey == key) {
                    trySend(getEmailPreference())
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getEmailPreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }.flowOn(Dispatchers.IO)

    private fun getIntPreference() : Int = sharedPreferences.getInt(priceKey, defaultPrice)

    override fun getPrice(): Flow<Int> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (priceKey == key) {
                    trySend(getIntPreference())
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getIntPreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }.flowOn(Dispatchers.IO)

}