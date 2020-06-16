package com.example.marvelstaff.ui.main

import android.view.ViewGroup
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.ui.BasePagedAdapter

class CharPagedAdapter(private val listener: (Character) -> Unit) :
    BasePagedAdapter<Character, CharListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharListHolder {
        return CharListHolder(parent)
    }

    override fun onBindViewHolder(holder: CharListHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            holder.bindNavigate(it, listener)
        }
    }
}