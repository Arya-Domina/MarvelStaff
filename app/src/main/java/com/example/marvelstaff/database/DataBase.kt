package com.example.marvelstaff.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelstaff.models.Character
import com.example.marvelstaff.models.Comic

@Database(entities = [Character::class, Comic::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): CharDao
}