package com.example.marvelstaff

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.marvelstaff.models.BaseResponse
import com.example.marvelstaff.repository.Repo

abstract class BaseViewModel<T : BaseResponse>(
    private val repository: Repo<T>,
    private val pageSize: Int = 10
) : ViewModel() {

    val query = MutableLiveData<String>()
    private val result = Transformations.map(query) {
        repository.getResponse(it, pageSize)
    }
    val list = Transformations.switchMap(result) {
        it.pagedList
    }
    val networkState = Transformations.switchMap(result) { it.networkState }
    val refreshState = Transformations.switchMap(result) { it.refreshState }

    fun request(query: String): Boolean {
        if (this.query.value == query)
            return false
        this.query.value = query
        return true
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        result.value?.retry?.invoke()
    }

    fun getCurrentQuery(): String? = query.value
}