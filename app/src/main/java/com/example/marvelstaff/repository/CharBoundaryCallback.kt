package com.example.marvelstaff.repository

import androidx.paging.PagedList
import com.example.marvelstaff.util.Logger
import com.example.marvelstaff.util.PagingRequestHelper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class CharBoundaryCallback<T, E>(
    private val query: String,
    private val getPage: (String, Int, Int) -> Single<T>,
    private val handleResponse: (T) -> Completable,
    private val pageSize: Int
) : PagedList.BoundaryCallback<E>() {

    val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())
    val networkState = helper.createStatusLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var offsetCount = 0

    private fun loadList(offset: Int, pagingRequest: PagingRequestHelper.Request.Callback) {
        getPage(query, offset, pageSize)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { handleResponse(it) }
            .subscribeBy(onComplete = {
                pagingRequest.recordSuccess()
                offsetCount += pageSize
            }, onError = {
                Logger.log("CharBoundaryCallback", "loadList", it)
                pagingRequest.recordFailure(it)
            }).addTo(compositeDisposable)
    }

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(
            PagingRequestHelper.RequestType.INITIAL,
            object : PagingRequestHelper.Request {
                override fun run(callback: PagingRequestHelper.Request.Callback) {
                    loadList(offsetCount, callback)
                }
            })
    }

    override fun onItemAtEndLoaded(itemAtEnd: E) {
        helper.runIfNotRunning(
            PagingRequestHelper.RequestType.AFTER,
            object : PagingRequestHelper.Request {
                override fun run(callback: PagingRequestHelper.Request.Callback) {
                    loadList(offsetCount, callback)
                }
            })
    }


    fun cleared() {
        compositeDisposable.clear()
    }

    fun retryPetitions() = helper.retryAllFailed()

}