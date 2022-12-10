package ru.naa.citydb.model

import androidx.lifecycle.LiveData
import ru.naa.citydb.room.CityEntity
import ru.naa.citydb.room.LocalDb

class CityRepository(var dataBase: LocalDb) {

    var cities: LiveData<List<CityEntity>> = dataBase.cityDao().getAll()

    fun getAll():LiveData<List<CityEntity>> {
        return cities
    }

    fun getCityById(id:Int):LiveData<List<CityEntity>> {
        return dataBase.cityDao().getCityById(id)
    }

    suspend fun insert(city:CityEntity){
        dataBase.cityDao().insert(city)
    }

    suspend fun delete(city:CityEntity){
        dataBase.cityDao().delete(city)
    }
    suspend fun update(city:CityEntity){
        dataBase.cityDao().update(city)
    }

}