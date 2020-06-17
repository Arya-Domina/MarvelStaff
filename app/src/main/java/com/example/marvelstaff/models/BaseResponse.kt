package com.example.marvelstaff.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
abstract class BaseResponse(
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var thumbnail: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseResponse) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (thumbnail != other.thumbnail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        return result
    }
}