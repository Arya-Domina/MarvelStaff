package com.example.marvelstaff.util

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelstaff.models.BaseResponse

class Constants {
    companion object {
        // network request
        const val BASE_URL = "https://gateway.marvel.com"
        const val HASH = "aa8d520376eac2d5241b86694e1a4c45"
        const val API_KEY = "2aa0efefe296da7fe3e020f0ceece007"
        const val TS = "1"

        const val SIZE_MEDIUM = "/standard_medium"
        const val SIZE_SMALL = "/standard_small"

        fun <T : BaseResponse> diffCallback() = object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem == newItem
        }
    }
}