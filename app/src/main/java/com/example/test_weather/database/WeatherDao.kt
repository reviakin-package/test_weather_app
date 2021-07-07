package com.example.test_weather.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun findLast(): LiveData<WeatherRow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDb(weatherRow: WeatherRow)
}