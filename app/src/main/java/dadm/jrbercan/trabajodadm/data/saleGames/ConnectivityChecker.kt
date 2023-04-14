package dadm.jrbercan.trabajodadm.data.saleGames

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(val connectivityManager: ConnectivityManager) {

    fun isConnectionAvailable(): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return (capabilities != null &&
                (capabilities.hasTransport(TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(TRANSPORT_WIFI)))
    }


}