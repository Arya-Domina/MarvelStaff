package com.example.marvelstaff.models

import androidx.room.Entity

@Entity
class Comic(
    id: Int? = null,
    name: String? = null,
    description: String? = null,
    val format: String? = null,
    val pageCount: Int? = null,
    val seriesName: String? = null,
    val seriesURI: String? = null,
    thumbnail: String? = null
) : BaseResponse(id, name, description, thumbnail) {
    var ownerId: Int? = null

    override fun toString(): String {
        return "\nComic(" +
                "id=$id, " +
                "title=$name, " +
                "description=${description?.take(8)}, " +
                "format=$format, " +
                "pageCount=$pageCount, " +
                "seriesName=$seriesName, " +
                "seriesURI=${seriesURI?.drop(42)}, " +
                "thumbnail=${thumbnail?.drop(38)}" +
                ")"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Comic) return false
        if (!super.equals(other)) return false

        if (format != other.format) return false
        if (pageCount != other.pageCount) return false
        if (seriesName != other.seriesName) return false
        if (seriesURI != other.seriesURI) return false
        if (ownerId != other.ownerId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (format?.hashCode() ?: 0)
        result = 31 * result + (pageCount ?: 0)
        result = 31 * result + (seriesName?.hashCode() ?: 0)
        result = 31 * result + (seriesURI?.hashCode() ?: 0)
        result = 31 * result + (ownerId ?: 0)
        return result
    }
}