package com.example.marvelstaff.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.marvelstaff.database.CharDao
import com.example.marvelstaff.models.BaseResponse
import com.example.marvelstaff.models.BaseResponseList
import com.example.marvelstaff.models.State
import com.example.marvelstaff.rest.ApiService
import com.example.marvelstaff.util.Logger
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : BaseResponse> : KoinComponent {

    protected val apiService: ApiService by inject()
    protected val database: CharDao by inject()
    protected lateinit var boundaryCallback: PageBoundaryCallback<T>
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun getResponse(query: String, pageSize: Int): Listing<T>

    protected abstract fun loadList(
        name: String, offset: Int, limit: Int
    ): Single<BaseResponseList<T>>

    protected abstract fun insertIntoDb(body: BaseResponseList<T>): Completable

    protected fun getBoundaryCallback(query: String, pageSize: Int): PageBoundaryCallback<T> =
        PageBoundaryCallback(
            query, this::load, pageSize
        )

    protected fun configurePagedList(pageSize: Int): PagedList.Config =
        PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

    private fun load(name: String, offset: Int, limit: Int): Completable {
        return loadList(name, offset, limit)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { insertIntoDb(it) }
    }

    fun refresh(name: String): LiveData<State> {
        compositeDisposable.clear()
        val refreshState = MutableLiveData<State>()
        refreshState.value = State.LOADING
        load(name, 0, 10)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logger.log("BaseRepository", "refresh success")
                refreshState.value = State.DONE
            }, {
                Logger.log("BaseRepository", "refresh err", it)
                refreshState.value = State.ERROR
            }).addTo(compositeDisposable)
        return refreshState
    }

    fun clear() {
        compositeDisposable.clear()
        boundaryCallback.clear()
    }
}