package com.example.test_weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherRow(
    @PrimaryKey
    val id: Long,
    var zipCode: String,
    var location: String,
    var tempterature: Double,
    var windSpeed: Double,
    var humidity: Int,
    var visibility: Int,
    var sunsirse: Int,
    var sunset: Int
)