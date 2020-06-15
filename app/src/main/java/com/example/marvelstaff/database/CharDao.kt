package com.example.marvelstaff.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.Comic
import io.reactivex.Completable

@Dao
interface CharDao {

    @Query("SELECT * FROM Character WHERE Character.name LIKE :query || '%'")
    fun getCharacters(query: String): DataSource.Factory<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(vararg characters: Character): Completable

    @Query("SELECT * FROM Comic WHERE Comic.ownerId LIKE :query")
    fun getComic(query: Int): DataSource.Factory<Int, Comic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(vararg comics: Comic): Completable

}