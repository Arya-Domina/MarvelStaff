package com.example.marvelstaff.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.marvelstaff.models.Character

class CharDataSourceFactory(var query: String? = null) : DataSource.Factory<Int, Character>() {

    val charDataSourceLiveData = MutableLiveData<CharDataSource>()

    override fun create(): DataSource<Int, Character> {
        val charDataSource = CharDataSource(query)
        charDataSourceLiveData.postValue(charDataSource)
        return charDataSource
    }

    fun updateQuery(query: String) {
        this.query = query
        charDataSourceLiveData.value?.refresh()
    }
}