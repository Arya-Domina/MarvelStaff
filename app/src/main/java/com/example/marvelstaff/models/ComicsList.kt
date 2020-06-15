package com.example.marvelstaff.models

data class ComicsList(val list: ArrayList<Comic> = arrayListOf()) {
    fun setOwner(ownerId: Int): ComicsList {
        list.forEach {
            it.ownerId = ownerId
        }
        return this
    }
}