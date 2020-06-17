package com.example.marvelstaff.ui.main

import com.example.marvelstaff.BaseViewModel
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.repository.CharRepository

class MainViewModel(
    charRepository: CharRepository
) : BaseViewModel<Character>(charRepository, 10)