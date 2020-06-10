package com.example.marvelstaff.models

import android.os.Parcel
import android.os.Parcelable

data class Character(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val comicsCount: Int? = null,
    val storiesCount: Int? = null,
    val eventsCount: Int? = null,
    val seriesCount: Int? = null,
    val thumbnail: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

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

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}