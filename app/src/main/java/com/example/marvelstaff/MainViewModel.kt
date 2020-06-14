package com.example.marvelstaff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.models.State
import com.example.marvelstaff.repository.CharacterRepository
import com.example.marvelstaff.ui.main.CharDataSource
import com.example.marvelstaff.ui.main.CharDataSourceFactory
import com.example.marvelstaff.util.Logger
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    val charactersList = MutableLiveData<CharactersList>(CharactersList())
    val comicsList = MutableLiveData<ComicsList>(ComicsList())
    val errorState = MutableLiveData<Boolean>(false)
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val pageSize = 5
    private val charDataSourceFactory = CharDataSourceFactory()
    val charList: LiveData<PagedList<Character>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        LivePagedListBuilder<Int, Character>(charDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> =
        Transformations.switchMap<CharDataSource, State>(
            charDataSourceFactory.charDataSourceLiveData, CharDataSource::state
        )

    fun listIsEmpty(): Boolean {
        return charList.value?.isEmpty() ?: true
    }

    fun fetch(query: String) {
        if (charDataSourceFactory.query != query) {
            charDataSourceFactory.updateQuery(query)
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun requestCharacters(name: String) {
        val disposable = repository.getCharacters(name)
            .subscribe({
                Logger.log("MainViewModel", "list success, $it")
                errorState.value = false
                charactersList.value = it
            }, {
                Logger.log("MainViewModel", "list err", it)
                errorState.value = true
            })
        compositeDisposable.addAll(disposable)
    }

    fun requestComics(characterId: Int) {
        val disposable = repository.getComics(characterId)
            .subscribe({
                Logger.log("MainViewModel", "comics success, $it")
                errorState.value = false
                comicsList.value = it
            }, {
                Logger.log("MainViewModel", "comics err, $it")
                errorState.value = true
            })
        compositeDisposable.addAll(disposable)
    }

    fun clearComics() {
        comicsList.value = ComicsList()
    }
}
