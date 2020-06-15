package com.example.marvelstaff.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.marvelstaff.repository.ComicRepository
import io.reactivex.disposables.CompositeDisposable

class ComicViewModel(
    private val comicRepository: ComicRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val pageSize = 5

    val ownerId = MutableLiveData<String>()
    val repoResult = Transformations.map(ownerId) {
        comicRepository.getResponse(it, pageSize)
    }
    val list = Transformations.switchMap(repoResult) { it.pagedList }
    val networkState = Transformations.switchMap(repoResult) { it.networkState }
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun retry() {
        repoResult.value?.retry?.invoke()
    }

    fun showChar(name: String): Boolean {
        if (this.ownerId.value == name)
            return false
        this.ownerId.value = name
        return true
    }

    fun currentCharName(): String? = ownerId.value // for onSaveInstanceState

}