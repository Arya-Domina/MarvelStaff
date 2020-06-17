package com.example.marvelstaff.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListHolder<T>(parent: ViewGroup, @LayoutRes layout: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layout, parent, false)
    ) {
    abstract fun bind(item: T)
}