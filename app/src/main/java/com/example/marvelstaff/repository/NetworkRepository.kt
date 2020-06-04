package com.example.marvelstaff.repository

import com.example.marvelstaff.models.Character
import com.example.marvelstaff.rest.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkRepository : KoinComponent {
    private val apiService: ApiService by inject()

    fun getCharacter(name: String): Single<Character> {
        return apiService.getCharacter(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}