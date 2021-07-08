package com.example.test_weather.utils

import com.example.test_weather.R

class ConvertVisibilityUtils {
    companion object{
        fun convertVisibilityToString(visibility: Int): Int{
            when{
                visibility >= 10000 -> return R.string.clear
                visibility >= 1000 -> return R.string.reduced
                visibility > 500 && visibility < 1000 -> return R.string.fog
                visibility < 500 -> return R.string.almost_zero
            }
            return R.string.invalid
        }
    }
}