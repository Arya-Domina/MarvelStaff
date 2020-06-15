package com.example.marvelstaff

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.marvelstaff.repository.CharRepository
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val charRepository: CharRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val pageSize = 10

    val name = MutableLiveData<String>()
    val repoResult = Transformations.map(name) {
        charRepository.getResponse(it, pageSize)
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
        if (this.name.value == name)
            return false
        this.name.value = name
        return true
    }

    fun currentCharName(): String? = name.value // for onSaveInstanceState

}