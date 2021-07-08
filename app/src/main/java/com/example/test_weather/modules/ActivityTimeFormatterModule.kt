package com.example.test_weather.modules

import android.icu.text.SimpleDateFormat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.format.DateTimeFormatter

@InstallIn(ActivityComponent::class)
@Module
class ActivityTimeFormatterModule {
    @Provides
    fun provideDateTimeFormatter() : SimpleDateFormat{
        return SimpleDateFormat("dd/MM/yyyy hh:mm:ss a O")
    }
}