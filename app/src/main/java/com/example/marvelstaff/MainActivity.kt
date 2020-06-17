package com.example.marvelstaff

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.SearchEvent
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.marvelstaff.ui.main.MainViewModel
import com.example.marvelstaff.util.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    companion object {
        private var queryText: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
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
            viewModel.request(query)
            Navigation.findNavController(this, R.id.nav_host_fragment).let {
                if (it.currentDestination?.id != R.id.charListFragment) {
                    Logger.log("MainActivity", "no charListFragment")
                    it.navigate(NavGraphDirections.actionGlobalCharListFragment(query))
                }
            }
        }
        super.onNewIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setQuery(queryText, false)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean = false

                override fun onQueryTextChange(p0: String?): Boolean {
                    queryText = p0
                    return false
                }

            })
            setOnSuggestionListener(object : SearchView.OnSuggestionListener {
                override fun onSuggestionSelect(position: Int): Boolean = false

                override fun onSuggestionClick(position: Int): Boolean {
                    val cursor: Cursor = suggestionsAdapter.getItem(position) as Cursor
                    val str: String =
                        cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                    setQuery(str, true)
                    return true
                }
            })
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
