package com.example.marvelstaff.ui.details

import com.example.marvelstaff.BaseViewModel
import com.example.marvelstaff.models.Comic
import com.example.marvelstaff.repository.ComicRepository

class ComicViewModel(
    comicRepository: ComicRepository
) : BaseViewModel<Comic>(comicRepository, 5)