package com.example.marvelstaff.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelstaff.BaseViewModel
import com.example.marvelstaff.models.BaseResponse
import com.example.marvelstaff.models.State
import com.example.marvelstaff.util.Logger
import kotlinx.android.synthetic.main.main_activity.*

abstract class BaseFragment<T : BaseResponse, H : BaseListHolder<T>>(@LayoutRes layoutRes: Int) :
    Fragment(layoutRes) {

    abstract val adapter: BasePagedAdapter<T, H>

    abstract fun getRecycler(): RecyclerView

    abstract fun getViewModel(): BaseViewModel<T>

    abstract fun bind()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRecycler().also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
        }
        bind()
        activity?.error_view?.bindErrorView()
        activity?.swipe_refresh?.bindSwipeRefresh()
    }

    private fun ErrorView.bindErrorView() {
        visibility = View.GONE
        setOnRetry {
            Logger.log("BaseFragment", "error_view retry")
            getViewModel().retry()
        }
        getViewModel().networkState.observe(viewLifecycleOwner, Observer { state ->
            visibility = if (state == State.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun SwipeRefreshLayout.bindSwipeRefresh() {
        isRefreshing = false
        setOnRefreshListener {
            Logger.log("BaseFragment", "swipe_refresh refresh")
            if (getViewModel().getCurrentQuery() == null)
                isRefreshing = false
            else
                getViewModel().refresh()
        }
        getViewModel().refreshState.observe(viewLifecycleOwner, Observer { state ->
            isRefreshing = state == State.LOADING
        })
    }
}