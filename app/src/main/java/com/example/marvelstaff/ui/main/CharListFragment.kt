package com.example.marvelstaff.ui.main

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.ui.BaseFragment
import com.example.marvelstaff.ui.BasePagedAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharListFragment : BaseFragment<Character, CharListHolder>(R.layout.main_fragment) {

    private val viewModel: MainViewModel by sharedViewModel()
    override val adapter: BasePagedAdapter<Character, CharListHolder> by lazy {
        CharPagedAdapter(this::navigateToCharacter)
    }

    override fun getRecycler(): RecyclerView = recycler_container

    override fun bind() {
        adapter.submitList(null)
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun navigateToCharacter(char: Character) {
        val action = CharListFragmentDirections.actionCharListFragmentToCharDetailsFragment(char)
        findNavController().navigate(action)
    }

}
