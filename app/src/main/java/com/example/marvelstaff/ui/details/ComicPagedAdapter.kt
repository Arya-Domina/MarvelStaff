package com.example.marvelstaff.ui.details

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelstaff.models.Comic

class ComicPagedAdapter : PagedListAdapter<Comic, ComicListHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListHolder {
        return ComicListHolder(parent)
    }

    override fun onBindViewHolder(holder: ComicListHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}