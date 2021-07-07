package com.example.test_weather.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherRow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}