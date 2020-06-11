package com.example.marvelstaff.rest

import android.content.Context
import android.net.ConnectivityManager
import com.example.marvelstaff.util.Constants
import com.example.marvelstaff.util.Logger
import okhttp3.Cache
import okhttp3.Interceptor

class Interceptors(context: Context) {
    val interceptor: Interceptor
    val cache = Cache(context.cacheDir, Constants.CACHE_SIZE)

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
            val reqBuilder = if (hasNetwork(context)) {
                Logger.log("Interceptors", "hasNetwork")
                request.newBuilder()
                    .header("Cache-Control", "public, max-age=${Constants.CACHE_TIME}")
            } else {
                Logger.log("Interceptors", "else")
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=${Constants.CACHE_LONG_TIME}"
                )
            }
            chain.proceed(reqBuilder.build())
        }
    }

    private fun hasNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return (activeNetwork?.isConnected == true)
            .also {
                Logger.log("Interceptors", "hasNetwork: $it")
            }
    }
}