package com.example.marvelstaff

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.SearchEvent
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelstaff.ui.main.CharListFragment
import com.example.marvelstaff.util.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Logger.log("MainActivity", "onNewIntent")
        intent?.getStringExtra(SearchManager.QUERY)?.also { query ->
            SearchRecentSuggestions(
                this,
                CharacterSuggestionProvider.AUTHORITY,
                CharacterSuggestionProvider.MODE
            )
                .saveRecentQuery(query, null)
            Logger.log("MainActivity", "onNewIntent, query: $query")
            (supportFragmentManager.findFragmentById(R.id.container) as CharListFragment)
                .searchCharacters(query)
        }
        super.onNewIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            isFocusable = true
            isIconified = false
            requestFocusFromTouch()
        }
        return true
    }

    override fun onSearchRequested(searchEvent: SearchEvent?): Boolean {
        Logger.log("MainActivity", "onSearchRequested, searchEvent: $searchEvent")
        return super.onSearchRequested(searchEvent)
    }
}
