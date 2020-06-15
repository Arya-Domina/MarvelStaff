package com.example.marvelstaff.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comic(
    @PrimaryKey val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val format: String? = null,
    val pageCount: Int? = null,
    val seriesName: String? = null,
    val seriesURI: String? = null,
    val thumbnail: String? = null
) {
    override fun toString(): String {
        return "\nComic(" +
                "id=$id, " +
                "title=$title, " +
                "description=${description?.take(8)}, " +
                "format=$format, " +
                "pageCount=$pageCount, " +
                "seriesName=$seriesName, " +
                "seriesURI=${seriesURI?.drop(42)}, " +
                "thumbnail=${thumbnail?.drop(38)}" +
                ")"
    }
}