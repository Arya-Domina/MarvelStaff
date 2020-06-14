package com.example.marvelstaff.repository

import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.rest.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkRepository : KoinComponent {
    private val apiService: ApiService by inject()

    fun getCharacters(name: String, offset: Int, limit: Int): Single<CharactersList> {
        return apiService.getCharacters(name, offset, limit)
    }

    fun getCharacters(name: String): Single<CharactersList> {
        return apiService.getCharacters(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getComics(characterId: Int): Single<ComicsList> {
        return apiService.getComics(characterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}