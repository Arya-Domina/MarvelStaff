package com.example.marvelstaff.ui

import androidx.paging.PagedListAdapter
import com.example.marvelstaff.models.BaseResponse
import com.example.marvelstaff.util.Constants

abstract class BasePagedAdapter<T : BaseResponse, H : BaseListHolder<T>> :
    PagedListAdapter<T, H>(Constants.diffCallback<T>()) {

    override fun onBindViewHolder(holder: H, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}