package com.example.marvelstaff.rest

import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters/{characterId}")
    fun getCharacter(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String = Constants.TS,
        @Query("hash") hash: String = Constants.HASH,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): Single<CharactersList>

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("nameStartsWith") name: String,
        @Query("ts") ts: String = Constants.TS,
        @Query("hash") hash: String = Constants.HASH,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): Single<CharactersList>

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String = Constants.TS,
        @Query("hash") hash: String = Constants.HASH,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): Single<ComicsList>
}