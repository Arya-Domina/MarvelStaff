package com.example.marvelstaff

import android.content.SearchRecentSuggestionsProvider

class CharacterSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.example.marvelstaff.CharacterSuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}