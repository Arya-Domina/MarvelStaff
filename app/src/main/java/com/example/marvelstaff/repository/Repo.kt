package com.example.marvelstaff.repository

import com.example.marvelstaff.models.Character

interface Repo {
    fun getCharacters(name: String, pageSize: Int): Listing<Character>
}