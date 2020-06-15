package com.example.marvelstaff.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.ui.PairTextView
import com.example.marvelstaff.util.addSizeSmall
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_view.view.*

class ComicListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.comic_view, parent, false)
) {

    fun bind(comic: Comic) {
        Picasso.get().load(comic.thumbnail?.addSizeSmall()).fit()
            .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
            .into(itemView.thumbnail)

        itemView.fields_list.pair_name.value = comic.title ?: "no name"
        itemView.fields_list.pair_des.value = comic.description?.take(40)?.plus("...")

        itemView.second_field_list.removeAllViews()
        itemView.second_field_list.addPair(R.string.format, comic.format)
        itemView.second_field_list.addPair(R.string.pages, comic.pageCount)
        itemView.second_field_list.addPair(R.string.series_name, comic.seriesName)
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: String?) {
        value?.let {
            this.addView(
                PairTextView(itemView.context, denotationRes, value)
            )
        }
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: Int?) {
        if (value != null && value != 0) {
            this.addPair(denotationRes, value.toString())
        }
    }
}