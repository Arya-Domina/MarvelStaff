package com.example.marvelstaff.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.example.marvelstaff.database.CharDao
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.models.State
import com.example.marvelstaff.rest.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class ComicRepository : Repo<Comic>, KoinComponent {
    private val apiService: ApiService by inject()
    private val database: CharDao by inject()

    override fun getResponse(query: String, pageSize: Int): Listing<Comic> {
        val boundaryCallback = CharBoundaryCallback<ComicsList, Comic>(
            query, this::loadList, this::insertIntoDb, pageSize
        )
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh(query)
        }
        val livePagedList = LivePagedListBuilder<Int, Comic>(
            database.getComic(query.toInt()),
            pageSize
        ) // config instead pageSize
            .setBoundaryCallback(boundaryCallback)
            .build()
        return Listing(livePagedList, boundaryCallback.networkState, refreshState,
            refresh = { refreshTrigger.value = null },
            retry = { boundaryCallback.helper.retryAllFailed() })
    }

    private fun loadList(characterId: String, offset: Int, limit: Int): Single<ComicsList> {
        return apiService.getComics(characterId.toInt(), offset, limit).map { it.setOwner(characterId.toInt()) }
    }

    private fun insertIntoDb(body: ComicsList): Completable {
        return database.insertComics(*body.list.toTypedArray())
    }

    private fun refresh(query: String): LiveData<State> {
        val networkState = MutableLiveData<State>()
        networkState.value = State.LOADING
        apiService.getComics(query.toInt(), 0, 10).subscribe({
            insertIntoDb(it)
            networkState.value = State.DONE
        }, {
            networkState.value = State.ERROR
        })
        return networkState
    }
}