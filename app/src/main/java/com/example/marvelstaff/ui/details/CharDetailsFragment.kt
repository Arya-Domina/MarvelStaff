package com.example.marvelstaff.ui.details

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.BaseViewModel
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.ui.BaseFragment
import com.example.marvelstaff.ui.BasePagedAdapter
import com.example.marvelstaff.util.addPair
import com.example.marvelstaff.util.addSizeMedium
import com.example.marvelstaff.util.loadThumbnail
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharDetailsFragment : BaseFragment<Comic, ComicListHolder>(R.layout.details_fragment) {

    private val viewModel: ComicViewModel by viewModel()
    override val adapter: BasePagedAdapter<Comic, ComicListHolder> by lazy {
        ComicPagedAdapter()
    }

    override fun getRecycler(): RecyclerView = recycler_container

    override fun getViewModel(): BaseViewModel<Comic> = viewModel

    override fun bind() {
        val char = CharDetailsFragmentArgs.fromBundle(requireArguments()).char

        thumbnail.loadThumbnail(char.thumbnail?.addSizeMedium())
        fields_list.addPair(R.string.char_name, char.name)
        fields_list.addPair(R.string.description, char.description)
        fields_list.addPair(R.string.comics, char.comicsCount)
        fields_list.addPair(R.string.series, char.seriesCount)
        fields_list.addPair(R.string.stories, char.storiesCount)
        fields_list.addPair(R.string.events, char.eventsCount)

        char.id?.toString()?.let { viewModel.request(it) }
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}