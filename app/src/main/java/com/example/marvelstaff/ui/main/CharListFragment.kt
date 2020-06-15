package com.example.marvelstaff.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelstaff.MainViewModel
import com.example.marvelstaff.R
import com.example.marvelstaff.util.Logger
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharListFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.log("CharListFragment", "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private val adapter: CharPagedAdapter by lazy {
        CharPagedAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.log("CharListFragment", "onActivityCreated")

        recycler_container.layoutManager = LinearLayoutManager(this.context)
        recycler_container.adapter = adapter

        adapter.submitList(null)
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
