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


    private val defaultPrice: String = "20"

    private fun getEmailPreference() : String = sharedPreferences.getString("email", "") ?: ""

    override fun getEmail(): Flow<String> = callbackFlow {
        val emailKey : String = getEmailPreference()
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

    private fun getPricePreference() : String = sharedPreferences.getString("price", defaultPrice) ?: "20"

    override fun getPrice(): Flow<String> = callbackFlow {
        val priceKey : String = getPricePreference()
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (priceKey == key) {
                    trySend(getPricePreference())
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getPricePreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }.flowOn(Dispatchers.IO)

}