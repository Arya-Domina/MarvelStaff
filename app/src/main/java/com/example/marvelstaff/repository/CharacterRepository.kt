package com.example.marvelstaff.repository

import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.ComicsList
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterRepository : KoinComponent {
    private val networkRepository: NetworkRepository by inject()

    fun getCharacters(name: String, offset: Int, limit: Int): Single<CharactersList> {
        return networkRepository.getCharacters(name, offset, limit)
    }

    fun getCharacters(name: String): Single<CharactersList> {
        return networkRepository.getCharacters(name)
    }

    fun getComics(characterId: Int): Single<ComicsList> {
        return networkRepository.getComics(characterId)
    }
}