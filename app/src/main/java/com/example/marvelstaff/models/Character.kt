package com.example.marvelstaff.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity
class Character(
    id: Int? = null,
    name: String? = null,
    description: String? = null,
    val comicsCount: Int? = null,
    val storiesCount: Int? = null,
    val eventsCount: Int? = null,
    val seriesCount: Int? = null,
    thumbnail: String? = null
) : BaseResponse(id, name, description, thumbnail), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(comicsCount)
        parcel.writeValue(storiesCount)
        parcel.writeValue(eventsCount)
        parcel.writeValue(seriesCount)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Character) return false
        if (!super.equals(other)) return false

        if (comicsCount != other.comicsCount) return false
        if (storiesCount != other.storiesCount) return false
        if (eventsCount != other.eventsCount) return false
        if (seriesCount != other.seriesCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (comicsCount ?: 0)
        result = 31 * result + (storiesCount ?: 0)
        result = 31 * result + (eventsCount ?: 0)
        result = 31 * result + (seriesCount ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}