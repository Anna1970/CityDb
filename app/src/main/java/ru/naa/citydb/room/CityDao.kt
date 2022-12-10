package ru.naa.citydb.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CityDao {
    @Query("Select * from CityEntity")
    fun getAll(): LiveData<List<CityEntity>>

    @Query("Select * from CityEntity where id=:id limit 1")
    fun getCityById(id:Int): LiveData<List<CityEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(city: CityEntity)

    @Delete
    fun delete(city: CityEntity)

    @Update(onConflict = REPLACE)
    fun update(city: CityEntity)
}