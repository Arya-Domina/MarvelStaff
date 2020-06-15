package com.example.marvelstaff.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.example.marvelstaff.database.CharDao
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.State
import com.example.marvelstaff.rest.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharRepository : Repo, KoinComponent {
    private val apiService: ApiService by inject()
    private val database: CharDao by inject()

    override fun getCharacters(name: String, pageSize: Int): Listing<Character> {
        val boundaryCallback = CharBoundaryCallback<CharactersList, Character>(
            name, this::loadList, this::insertIntoDb, pageSize
        )
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh(name)
        }
        val livePagedList = LivePagedListBuilder<Int, Character>(
            database.getCharacters(name),
            pageSize
        ) // config instead pageSize
            .setBoundaryCallback(boundaryCallback)
            .build()
        return Listing(livePagedList, boundaryCallback.networkState, refreshState,
            refresh = { refreshTrigger.value = null },
            retry = { boundaryCallback.helper.retryAllFailed() })
    }

    private fun loadList(name: String, offset: Int, limit: Int): Single<CharactersList> {
        return apiService.getCharacters(name, offset, limit)
    }

    private fun insertIntoDb(body: CharactersList): Completable {
        return database.insert(*body.list.toTypedArray())
    }

    private fun refresh(name: String): LiveData<State> {
        val networkState = MutableLiveData<State>()
        networkState.value = State.LOADING
        apiService.getCharacters(name, 0, 10).subscribe({
            insertIntoDb(it)
            networkState.value = State.DONE
        }, {
            networkState.value = State.ERROR
        })
        return networkState
    }

}