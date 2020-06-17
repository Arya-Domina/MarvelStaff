package com.example.marvelstaff.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.example.marvelstaff.models.BaseResponseList
import com.example.marvelstaff.models.Comic
import io.reactivex.Completable
import io.reactivex.Single

class ComicRepository : BaseRepository<Comic>() {

    override fun getResponse(query: String, pageSize: Int): Listing<Comic> {
        boundaryCallback = getBoundaryCallback(query, pageSize)
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh(query)
        }
        val livePagedList = LivePagedListBuilder<Int, Comic>(
            database.getComic(query.toInt()),
            configurePagedList(pageSize)
        )
            .setBoundaryCallback(boundaryCallback)
            .build()
        return Listing(livePagedList, boundaryCallback.networkState, refreshState,
            refresh = { refreshTrigger.value = null },
            retry = { boundaryCallback.helper.retryAllFailed() })
    }

    override fun loadList(
        name: String, offset: Int, limit: Int
    ): Single<BaseResponseList<Comic>> {
        return apiService.getComics(name.toInt(), offset, limit)
            .map { it.setOwner(name.toInt()) }
    }

    override fun insertIntoDb(body: BaseResponseList<Comic>): Completable {
        return database.insertComics(*body.list.toTypedArray())
    }
}