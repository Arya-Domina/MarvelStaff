package com.example.marvelstaff

import com.example.marvelstaff.repository.CharacterRepository
import com.example.marvelstaff.repository.NetworkRepository
import com.example.marvelstaff.rest.Client
import com.example.marvelstaff.rest.Interceptors
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { NetworkRepository() }
    single { CharacterRepository() }
    single { Client().getApi() }
    single { Interceptors(androidApplication()) }
    viewModel { MainViewModel(get()) }

}
