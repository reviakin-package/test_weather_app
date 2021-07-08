package com.example.test_weather

import com.google.gson.annotations.SerializedName

data class WeatherInfo (

    @SerializedName("visibility") var visibility : Int,
    @SerializedName("main") var main : Main,
    @SerializedName("wind") var wind : Wind,
    @SerializedName("sys") var sys : Sys,
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String

)