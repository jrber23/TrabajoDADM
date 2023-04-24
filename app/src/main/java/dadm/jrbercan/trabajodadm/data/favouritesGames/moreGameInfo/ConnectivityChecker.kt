package dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(private val conManager: ConnectivityManager) {
    fun isConnectionAvailable(): Boolean {
        val capabilities = conManager.getNetworkCapabilities(conManager.activeNetwork)
        return (capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                )
    }
}