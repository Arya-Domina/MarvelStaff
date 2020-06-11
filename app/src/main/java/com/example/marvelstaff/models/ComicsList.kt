package com.example.marvelstaff.models

data class ComicsList(val list: ArrayList<Comic> = arrayListOf()) {
    val size: Int
        get() = list.size

    fun get(index: Int) = list[index]
}