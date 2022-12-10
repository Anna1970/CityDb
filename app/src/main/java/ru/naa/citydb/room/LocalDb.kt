package ru.naa.citydb.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1, exportSchema = true)
abstract class LocalDb : RoomDatabase(){
    abstract fun cityDao() : CityDao
}