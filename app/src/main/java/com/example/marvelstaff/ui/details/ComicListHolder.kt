package com.example.marvelstaff.ui.details

import android.view.ViewGroup
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.ui.BaseListHolder
import com.example.marvelstaff.util.addPair
import com.example.marvelstaff.util.addSizeSmall
import com.example.marvelstaff.util.loadThumbnail
import kotlinx.android.synthetic.main.comic_view.view.*

class ComicListHolder(parent: ViewGroup) :
    BaseListHolder<Comic>(parent, R.layout.comic_view) {

    override fun bind(item: Comic) {
        itemView.thumbnail.loadThumbnail(item.thumbnail?.addSizeSmall())

        itemView.fields_list.pair_name.value = item.name ?: "no name"
        itemView.fields_list.pair_des.value = item.description?.take(40)?.plus("...")

        itemView.second_field_list.removeAllViews()
        itemView.second_field_list.addPair(R.string.format, item.format)
        itemView.second_field_list.addPair(R.string.pages, item.pageCount)
        itemView.second_field_list.addPair(R.string.series_name, item.seriesName)
    }
}