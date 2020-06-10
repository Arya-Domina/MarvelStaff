package com.example.marvelstaff.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelstaff.MainViewModel
import com.example.marvelstaff.R
import com.example.marvelstaff.ui.PairTextView
import com.example.marvelstaff.util.Logger
import com.example.marvelstaff.util.addSizeMedium
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharDetailsFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.log("CharDetailsFragment", "onCreateView")
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Logger.log("CharDetailsFragment", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        val char = CharDetailsFragmentArgs.fromBundle(requireArguments()).char

        Picasso.get().load(char.thumbnail?.addSizeMedium()).fit()
            .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
            .into(thumbnail)
        fields_list.addPair(R.string.char_name, char.name)
        fields_list.addPair(R.string.description, char.description)
        fields_list.addPair(R.string.comics, char.comicsCount)
        fields_list.addPair(R.string.series, char.seriesCount)
        fields_list.addPair(R.string.stories, char.storiesCount)
        fields_list.addPair(R.string.events, char.eventsCount)

        recycler_container.layoutManager = LinearLayoutManager(this.context)
        recycler_container.adapter = ComicListAdapter()

        viewModel.comicsList.observe(viewLifecycleOwner, Observer {
            Logger.log("CharDetailsFragment", "comicsList observe ${it.size}")
            (recycler_container.adapter as ComicListAdapter).apply {
                listComic = it
                notifyDataSetChanged()
            }
        })
        if (char.id != null && char.comicsCount != 0)
            viewModel.requestComics(char.id)
        else
            viewModel.clearComics()
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: String?) {
        value?.let {
            this.addView(
                PairTextView(
                    this@CharDetailsFragment.requireContext(), denotationRes, value
                )
            )
        }
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: Int?) {
        if (value != null && value != 0) {
            this.addPair(denotationRes, value.toString())
        }
    }
}