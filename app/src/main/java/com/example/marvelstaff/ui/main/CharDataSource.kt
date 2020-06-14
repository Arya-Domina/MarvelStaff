package com.example.marvelstaff.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.State
import com.example.marvelstaff.repository.NetworkRepository
import com.example.marvelstaff.util.Logger
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharDataSource(private val query: String?) : PositionalDataSource<Character>(),
    KoinComponent {

    private val networkRepository: NetworkRepository by inject()
    private val compositeDisposable = CompositeDisposable()
    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        query?.let { query ->
            state.postValue(State.LOADING)
            val disposable = networkRepository.getCharacters(
                query, params.requestedStartPosition, params.requestedLoadSize
            )
                .subscribe({
                    Logger.log(
                        "CharDataSource",
                        "loadInitial startPosition: ${params.requestedStartPosition}, loadSize: ${params.requestedLoadSize}"
                    )
                    state.postValue(State.DONE)
                    callback.onResult(it.list, 0)
                }, {
                    Logger.log("CharDataSource", "loadInitial err", it)
                    state.postValue(State.ERROR)
                })
            compositeDisposable.add(disposable)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        query?.let { query ->
            state.postValue(State.LOADING)
            val disposable =
                networkRepository.getCharacters(query, params.startPosition, params.loadSize)
                    .subscribe({
                        Logger.log(
                            "CharDataSource",
                            "loadRange startPosition: ${params.startPosition}, loadSize: ${params.loadSize}"
                        )
                        state.postValue(State.DONE)
                        callback.onResult(it.list)
                    }, {
                        Logger.log("CharDataSource", "loadRange err", it)
                        state.postValue(State.ERROR)
                    })
            compositeDisposable.add(disposable)
        }
    }

    fun refresh() = this.invalidate()

}