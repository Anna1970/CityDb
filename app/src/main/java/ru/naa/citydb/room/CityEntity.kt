package ru.naa.citydb.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var key: String,
    var name: String,
    var type: String
)

