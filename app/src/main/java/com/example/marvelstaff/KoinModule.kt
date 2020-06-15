package com.example.marvelstaff

import androidx.room.Room
import com.example.marvelstaff.database.DataBase
import com.example.marvelstaff.repository.CharRepository
import com.example.marvelstaff.repository.CharacterRepository
import com.example.marvelstaff.repository.NetworkRepository
import com.example.marvelstaff.rest.Client
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { NetworkRepository() }
    single { CharacterRepository() }
    single { CharRepository() }
    single { Client().getApi() }
    single {
        Room.databaseBuilder(androidApplication(), DataBase::class.java, "database-name")
            .build().getDao()
    }
    viewModel { MainViewModel(get(), get()) }

}
