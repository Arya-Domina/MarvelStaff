package com.example.marvelstaff.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.models.Character
import kotlinx.android.synthetic.main.short_char_view.view.*

class CharListHolder(private val itemCharView: View) : RecyclerView.ViewHolder(itemCharView) {

    fun bind(char: Character) {
        // TODO set image

        itemCharView.fields_list.pair_name.value = char.name ?: "no name"
        itemCharView.fields_list.pair_des.value = char.description?.take(40)?.plus("...")
    }
}
