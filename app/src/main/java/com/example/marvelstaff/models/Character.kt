package com.example.marvelstaff.models

data class Character(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val comicsCount: Int? = null,
    val storiesCount: Int? = null,
    val eventsCount: Int? = null,
    val seriesCount: Int? = null,
    val thumbnail: String? = null
) {
    override fun toString(): String {
        return "\nCharacter(" +
                "id=$id, " +
                "name=$name, " +
                "description=${description?.take(8)}, " +
                "comicsCount=$comicsCount, " +
                "storiesCount=$storiesCount, " +
                "eventsCount=$eventsCount, " +
                "seriesCount=$seriesCount, " +
                "thumbnail=${thumbnail?.drop(38)}" +
                ")"
    }
}