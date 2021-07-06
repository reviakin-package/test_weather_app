package com.example.test_weather

import com.google.gson.annotations.SerializedName

data class Sys (

    @SerializedName("sunrise") var sunrise : Int,
    @SerializedName("sunset") var sunset : Int

)