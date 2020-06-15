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
import com.example.marvelstaff.R
import com.example.marvelstaff.ui.PairTextView
import com.example.marvelstaff.util.Logger
import com.example.marvelstaff.util.addSizeMedium
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharDetailsFragment : Fragment() {

    private val viewModel: ComicViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.log("CharDetailsFragment", "onCreateView")
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    private val adapter: ComicPagedAdapter by lazy {
        ComicPagedAdapter()
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
        recycler_container.adapter = adapter

        char.id?.toString()?.let { viewModel.showChar(it) }
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
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