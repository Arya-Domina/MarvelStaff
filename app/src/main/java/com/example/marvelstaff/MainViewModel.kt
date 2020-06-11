package com.example.marvelstaff

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelstaff.models.CharactersList
import com.example.marvelstaff.models.ComicsList
import com.example.marvelstaff.repository.CharacterRepository
import com.example.marvelstaff.util.Logger
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    val charactersList = MutableLiveData<CharactersList>(CharactersList())
    val comicsList = MutableLiveData<ComicsList>(ComicsList())
    val errorState = MutableLiveData<Boolean>(false)
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun requestCharacter(characterId: Int) {
        val disposable = repository.getCharacter(characterId)
            .subscribe({
                Logger.log("MainViewModel", "one success, $it")
            }, {
                Logger.log("MainViewModel", "one err", it)
            })
        compositeDisposable.addAll(disposable)
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
