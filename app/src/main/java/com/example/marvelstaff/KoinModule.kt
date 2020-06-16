package com.example.marvelstaff

import androidx.room.Room
import com.example.marvelstaff.database.DataBase
import com.example.marvelstaff.repository.CharRepository
import com.example.marvelstaff.repository.ComicRepository
import com.example.marvelstaff.rest.Client
import com.example.marvelstaff.ui.details.ComicViewModel
import com.example.marvelstaff.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { ComicRepository() }
    single { CharRepository() }
    single { Client().getApi() }
    single {
        Room.databaseBuilder(androidApplication(), DataBase::class.java, "database-name")
            .build().getDao()
    }
    viewModel { MainViewModel(get()) }
    viewModel { ComicViewModel(get()) }

}
