package com.example.marvelstaff.ui.main

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.R
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.util.addSizeSmall
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.short_char_view.view.*

class CharListHolder(private val itemCharView: View) : RecyclerView.ViewHolder(itemCharView) {

    fun bind(char: Character) {
        Picasso.get().load(char.thumbnail?.addSizeSmall()).fit()
            .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error)
            .into(itemCharView.thumbnail)

        itemCharView.fields_list.pair_name.value = char.name ?: "no name"
        itemCharView.fields_list.pair_des.value = char.description?.take(40)?.plus("...")

        itemCharView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                CharListFragmentDirections.actionCharListFragmentToCharDetailsFragment(char)
            )
        )
    }
}
