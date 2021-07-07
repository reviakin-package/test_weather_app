package com.example.test_weather

import com.google.gson.annotations.SerializedName

data class Main (

	@SerializedName("temp") var temp : Double,
	@SerializedName("humidity") var humidity : Int

)