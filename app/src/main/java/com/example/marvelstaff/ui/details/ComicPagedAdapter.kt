package com.example.marvelstaff.ui.details

import android.view.ViewGroup
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.ui.BasePagedAdapter

class ComicPagedAdapter : BasePagedAdapter<Comic, ComicListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListHolder {
        return ComicListHolder(parent)
    }

}