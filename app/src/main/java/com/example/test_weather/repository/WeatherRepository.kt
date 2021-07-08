package com.example.test_weather.repository

import com.example.test_weather.WeatherApi
import com.example.test_weather.WeatherInfo
import com.example.test_weather.database.WeatherDao
import com.example.test_weather.database.WeatherRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {

    val data = weatherDao.findLast()

    suspend fun refresh(zipCode: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            val weather: WeatherInfo = weatherApi.getWeatherOnZipCode(zipCode.toInt()).await()
            weatherDao.insertDb(
                WeatherRow(
                    0,
                    zipCode,
                    weather.name,
                    weather.main.temp,
                    weather.wind.speed,
                    weather.main.humidity,
                    weather.visibility,
                    weather.sys.sunrise,
                    weather.sys.sunset
                )
            )
        }
    }

}