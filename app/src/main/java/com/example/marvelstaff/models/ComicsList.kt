package com.example.marvelstaff.models

data class ComicsList(override val list: ArrayList<Comic> = arrayListOf()) :
    BaseResponseList<Comic>(list) {
    fun setOwner(ownerId: Int): ComicsList {
        list.forEach {
            it.ownerId = ownerId
        }
        return this
    }
}