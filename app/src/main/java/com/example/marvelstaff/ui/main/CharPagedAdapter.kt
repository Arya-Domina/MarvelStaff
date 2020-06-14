package com.example.marvelstaff.ui.main

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelstaff.models.Character

class CharPagedAdapter : PagedListAdapter<Character, CharListHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharListHolder {
        return CharListHolder(parent)
    }

    override fun onBindViewHolder(holder: CharListHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}