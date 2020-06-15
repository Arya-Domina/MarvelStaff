package com.example.marvelstaff.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelstaff.models.Character
import io.reactivex.Completable

@Dao
interface CharDao {

    @Query("SELECT * FROM Character WHERE Character.name LIKE :query || '%'")
    fun getCharacters(query: String): DataSource.Factory<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg characters: Character): Completable
}