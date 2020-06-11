package com.example.marvelstaff.rest

import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.CharactersListDeserializer
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.models.ComicsListDeserializer
import com.example.marvelstaff.util.Constants.Companion.BASE_URL
import com.example.marvelstaff.util.Logger
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Client : KoinComponent {
    private val interceptors: Interceptors by inject()

    private val apiService: ApiService by lazy {
        return@lazy createApiService()
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(interceptors.cache)
            .addInterceptor(interceptors.interceptor)
            .build()
    }

    private fun createApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        CharactersList::class.java, CharactersListDeserializer()
                    ).registerTypeAdapter(
                        ComicsList::class.java, ComicsListDeserializer()
                    ).create()
                )
            )
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    fun getApi(): ApiService {
        Logger.log("Client", "return api")
        return apiService
    }
}