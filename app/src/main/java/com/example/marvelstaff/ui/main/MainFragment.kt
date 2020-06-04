package com.example.marvelstaff.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.marvelstaff.R
import com.example.marvelstaff.util.Logger
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.log("MainFragment", "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.log("MainFragment", "onActivityCreated")
        button_one.setOnClickListener {
            if (!text_id.text.isNullOrEmpty())
//                viewModel.requestCharacter(text_id.text.toString().toInt())
                viewModel.requestComics(text_id.text.toString().toInt())
            else Logger.log("MainFragment", "text_id NullOrEmpty")
        }
        button_list.setOnClickListener {
            viewModel.requestCharacters(edit_text.text.toString())
        }
        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            it.list.getOrNull(1)?.let {
                text_name.text = it.name
                text_id.text = it.id.toString()
            }
        })
        viewModel.comicsList.observe(viewLifecycleOwner, Observer {
            Logger.log("MainFragment", "comicsList: $it")
        })
    }

}
