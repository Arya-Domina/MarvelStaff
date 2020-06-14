package com.example.marvelstaff.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.util.addSizeSmall
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.short_char_view.view.*

class CharListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.short_char_view, parent, false)
) {

    fun bind(char: Character) {
        Picasso.get().load(char.thumbnail?.addSizeSmall()).fit()
            .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
            .into(itemView.thumbnail)

        itemView.fields_list.pair_name.value = char.name ?: "no name"
        itemView.fields_list.pair_des.value = char.description?.take(40)?.plus("...")

        itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                CharListFragmentDirections.actionCharListFragmentToCharDetailsFragment(char)
            )
        )
    }
}
