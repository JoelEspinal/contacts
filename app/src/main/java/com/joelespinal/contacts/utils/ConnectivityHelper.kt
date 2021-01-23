package com.joelespinal.contacts.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.joelespinal.contacts.main.App

enum class Network {
    WIFI,
    CELULAR,
    VPN,
    NONE
}

class ConnectivityHelper private constructor() {
    companion object {
        fun getConnectionType(): Network {
            var result = Network.NONE
            val connectivityManager =
                App.getContext()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager?.run {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                        ?.run {
                            when {
                                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                                    result = Network.WIFI
                                }
                                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                                    result = Network.CELULAR
                                }
                                hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                                    result = Network.VPN
                                }
                            }
                        }
                }
            } else {
                connectivityManager?.run {
                    connectivityManager.activeNetworkInfo?.run {
                        when (type) {
                            ConnectivityManager.TYPE_WIFI -> {
                                result = Network.WIFI
                            }
                            ConnectivityManager.TYPE_MOBILE -> {
                                result = Network.CELULAR
                            }
                            ConnectivityManager.TYPE_VPN -> {
                                result = Network.VPN
                            }
                        }
                    }
                }
            }
            return result
        }
    }


}