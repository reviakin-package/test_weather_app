package com.example.test_weather

import android.content.Context
import androidx.room.Room
import com.example.test_weather.database.AppDatabase
import com.example.test_weather.database.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideWeatherDao(appDatabase: AppDatabase) : WeatherDao{
        return appDatabase.weatherDao
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }
}