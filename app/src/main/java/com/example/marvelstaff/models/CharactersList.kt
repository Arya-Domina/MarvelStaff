package com.example.marvelstaff.models

data class CharactersList(override val list: ArrayList<Character> = arrayListOf()) :
    BaseResponseList<Character>(list)