package com.example.marvelstaff.ui.main

import androidx.lifecycle.ViewModel
import com.example.marvelstaff.repository.CharacterRepository
import com.example.marvelstaff.util.Logger
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun requestCharacter() {
        val disposable = repository.getCharacter("spi")
            .subscribe({
                Logger.log("MainActivity", "success, $it")
            }, {
                Logger.log("MainActivity", "err", it)
            })
        compositeDisposable.addAll(disposable)
    }
}
