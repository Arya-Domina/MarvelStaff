package com.example.marvelstaff.ui.details

import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.ui.PairTextView
import com.example.marvelstaff.util.addSizeSmall
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_view.view.*

class ComicListHolder(private val itemComicView: View) : RecyclerView.ViewHolder(itemComicView) {

    fun bind(comic: Comic) {
        Picasso.get().load(comic.thumbnail?.addSizeSmall()).fit()
            .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
            .into(itemComicView.thumbnail)

        itemComicView.fields_list.pair_name.value = comic.title ?: "no name"
        itemComicView.fields_list.pair_des.value = comic.description?.take(40)?.plus("...")

        itemComicView.second_field_list.removeAllViews()
        itemComicView.second_field_list.addPair(R.string.format, comic.format)
        itemComicView.second_field_list.addPair(R.string.pages, comic.pageCount)
        itemComicView.second_field_list.addPair(R.string.series_name, comic.seriesName)
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: String?) {
        value?.let {
            this.addView(
                PairTextView(itemComicView.context, denotationRes, value)
            )
        }
    }

    private fun LinearLayout.addPair(@StringRes denotationRes: Int, value: Int?) {
        if (value != null && value != 0) {
            this.addPair(denotationRes, value.toString())
        }
    }
}