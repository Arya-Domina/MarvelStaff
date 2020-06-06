package com.example.marvelstaff.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelstaff.R
import com.example.marvelstaff.util.Logger
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharListFragment : Fragment() {

    companion object {
        fun newInstance() = CharListFragment()
    }

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

        button_one.setOnClickListener {
            viewModel.requestComics(1009157) // test
        }
        button_list.setOnClickListener {
            viewModel.requestCharacters(search_edit_text.text.toString())
        }
        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            (recycler_container.adapter as CharListAdapter).listChar = it
            (recycler_container.adapter as CharListAdapter).notifyDataSetChanged()
        })
        viewModel.comicsList.observe(viewLifecycleOwner, Observer {
            Logger.log("CharListFragment", "comicsList")
        })
    }

}
