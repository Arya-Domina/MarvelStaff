package com.example.marvelstaff.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.models.BaseResponse

abstract class BaseFragment<T : BaseResponse, H : BaseListHolder<T>>(@LayoutRes layoutRes: Int) :
    Fragment(layoutRes) {

    abstract val adapter: BasePagedAdapter<T, H>

    abstract fun getRecycler(): RecyclerView

    abstract fun bind()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRecycler().also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
        }
        bind()
    }
}