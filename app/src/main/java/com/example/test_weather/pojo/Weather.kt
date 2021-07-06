package com.example.test_weather

import com.google.gson.annotations.SerializedName

data class Weather (
    @SerializedName("main") var main : String
)