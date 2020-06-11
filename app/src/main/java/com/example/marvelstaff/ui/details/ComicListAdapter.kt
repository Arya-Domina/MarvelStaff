package com.example.marvelstaff.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.ComicsList

class ComicListAdapter(var listComic: ComicsList = ComicsList()) :
    RecyclerView.Adapter<ComicListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListHolder {
        return ComicListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comic_view, parent, false)
        )
    }

    override fun getItemCount(): Int = listComic.size

    override fun onBindViewHolder(holder: ComicListHolder, position: Int) {
        holder.bind(listComic.get(position))
    }
}