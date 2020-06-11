package com.example.marvelstaff.util

class Constants {
    companion object {
        // network request
        const val BASE_URL = "https://gateway.marvel.com"
        const val HASH = "aa8d520376eac2d5241b86694e1a4c45"
        const val API_KEY = "2aa0efefe296da7fe3e020f0ceece007"
        const val TS = "1"
        const val CACHE_SIZE = (5 * 1024 * 1024).toLong() // 5MB
        const val CACHE_TIME = (60 * 60) // hour
        const val CACHE_LONG_TIME = (60 * 60 * 24 * 7) // week

        const val SIZE_MEDIUM = "/standard_medium"
        const val SIZE_SMALL = "/standard_small"
    }
}