package com.example.test_weather

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class WeatherApiModule {
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    @Provides
    fun provideWeatherApi(retrofit: Retrofit) : WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }
}