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
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.log("CharListFragment", "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.log("CharListFragment", "onActivityCreated")

        recycler_container.layoutManager = LinearLayoutManager(this.context)
        recycler_container.adapter = CharListAdapter()

        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            Logger.log("CharListFragment", "charactersList observe ${it.list.size}")
            (recycler_container.adapter as CharListAdapter).listChar = it
            (recycler_container.adapter as CharListAdapter).notifyDataSetChanged()
        })
        viewModel.errorState.observe(viewLifecycleOwner, Observer {
            Logger.log("CharListFragment", "errorState: $it")
            text_error_state.visibility = if (it) View.VISIBLE else View.GONE
        })

        val q = CharListFragmentArgs.fromBundle(requireArguments()).query
        Logger.log("CharListFragment", "onActivityCreated args: $q")
        if (q != "null") {
            viewModel.requestCharacters(q)
        }
    }

}
