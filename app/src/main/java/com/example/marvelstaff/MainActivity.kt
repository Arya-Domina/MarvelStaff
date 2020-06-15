package com.example.marvelstaff

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.SearchEvent
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.marvelstaff.models.State
import com.example.marvelstaff.util.Logger
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel.networkState.observe(this, Observer { state ->
            Logger.log("MainActivity", "networkState: $state")
            progress_bar.visibility = if (state == State.LOADING) View.VISIBLE else View.INVISIBLE
            text_error_state.visibility = if (state == State.ERROR) View.VISIBLE else View.INVISIBLE
        })
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
            viewModel.showChar(query)
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
