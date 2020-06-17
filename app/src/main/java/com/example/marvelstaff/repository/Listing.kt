package com.example.marvelstaff.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.marvelstaff.models.State

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<State>,
    val refreshState: LiveData<State>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)