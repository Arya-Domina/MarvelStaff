package com.example.marvelstaff.repository

import com.example.marvelstaff.models.Character
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterRepository : KoinComponent {
    private val networkRepository: NetworkRepository by inject()

    fun getCharacter(name: String): Single<Character> {
        return networkRepository.getCharacter(name)
    }
}