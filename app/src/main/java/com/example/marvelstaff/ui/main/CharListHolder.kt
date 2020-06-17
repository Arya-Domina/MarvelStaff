package com.example.marvelstaff.ui.main

import android.view.ViewGroup
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.ui.BaseListHolder
import com.example.marvelstaff.util.addSizeSmall
import com.example.marvelstaff.util.loadThumbnail
import kotlinx.android.synthetic.main.short_char_view.view.*

class CharListHolder(parent: ViewGroup) :
    BaseListHolder<Character>(parent, R.layout.short_char_view) {

    override fun bind(item: Character) {
        itemView.thumbnail.loadThumbnail(item.thumbnail?.addSizeSmall())

        itemView.fields_list.pair_name.value = item.name ?: "no name"
        itemView.fields_list.pair_des.value = item.description?.take(40)?.plus("...")
    }

    fun bindNavigate(item: Character, l: (Character) -> Unit) {
        itemView.setOnClickListener {
            l.invoke(item)
        }
    }
}
