package com.example.marvelstaff.repository

interface Repo<T> {
    fun getResponse(query: String, pageSize: Int): Listing<T>
}