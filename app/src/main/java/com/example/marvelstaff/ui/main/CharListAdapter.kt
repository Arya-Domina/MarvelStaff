package com.example.marvelstaff.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelstaff.models.CharactersList

class CharListAdapter(var listChar: CharactersList = CharactersList()) :
    RecyclerView.Adapter<CharListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharListHolder {
        return CharListHolder(parent)
    }

    override fun getItemCount(): Int = listChar.list.size

    override fun onBindViewHolder(holder: CharListHolder, position: Int) {
        holder.bind(listChar.list[position])
    }
}
