package com.example.test_weather

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?&lang=en&units=metric&appid=0d95f07a988ae1b9467cd7c7c54f67cc")
    fun getWeatherOnZipCode(@Query("q") zipCode: Int): Deferred<WeatherInfo>

}