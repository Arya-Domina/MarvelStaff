package com.example.marvelstaff.rest

import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.CharacterDeserializer
import com.example.marvelstaff.util.Constants.Companion.BASE_URL
import com.example.marvelstaff.util.Logger
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    private val apiService: ApiService by lazy {
        return@lazy createApiService()
    }

    private fun createApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Character::class.java, CharacterDeserializer()
                    ).create()
                )
            )
            .build()
            .create(ApiService::class.java)
    }

    fun getApi(): ApiService {
        Logger.log("Client", "return api")
        return apiService
    }
}