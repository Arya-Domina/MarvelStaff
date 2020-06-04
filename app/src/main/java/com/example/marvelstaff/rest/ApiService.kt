package com.example.marvelstaff.rest

import com.example.marvelstaff.models.Character
import com.example.marvelstaff.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    fun getCharacter(
        @Query("nameStartsWith") name: String,
        @Query("ts") ts: String = Constants.TS,
        @Query("hash") hash: String = Constants.HASH,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): Single<Character>
}