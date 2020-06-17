package com.example.marvelstaff.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.example.marvelstaff.models.BaseResponseList
import com.example.marvelstaff.models.Character
import io.reactivex.Completable
import io.reactivex.Single

class CharRepository : BaseRepository<Character>() {

    override fun getResponse(query: String, pageSize: Int): Listing<Character> {
        val boundaryCallback = PageBoundaryCallback<Character>(
            query, this::load, pageSize
        )
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh(query)
        }
        val livePagedList = LivePagedListBuilder<Int, Character>(
            database.getCharacters(query),
            configurePagedList(pageSize)
        )
            .setBoundaryCallback(boundaryCallback)
            .build()
        return Listing(livePagedList, boundaryCallback.networkState,
            refreshState,
            refresh = { refreshTrigger.value = null },
            retry = { boundaryCallback.helper.retryAllFailed() })
    }

    override fun loadList(
        name: String, offset: Int, limit: Int
    ): Single<BaseResponseList<Character>> {
        return apiService.getCharacters(name, offset, limit)
            .map { it }
    }

    override fun insertIntoDb(body: BaseResponseList<Character>): Completable {
        return database.insertCharacters(*body.list.toTypedArray())
    }
}