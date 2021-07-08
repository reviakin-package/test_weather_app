package com.example.test_weather.activity

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.test_weather.R
import com.example.test_weather.database.WeatherRow
import com.example.test_weather.helper.LoadingState
import com.example.test_weather.utils.ConvertVisibilityUtils
import com.example.test_weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val weatherViewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var dateFormatter: SimpleDateFormat
    private lateinit var containerInfo: LinearLayout
    private lateinit var editZipCode: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var textLocation: TextView
    private lateinit var textTemperature: TextView
    private lateinit var textWindSpeed: TextView
    private lateinit var textHumidity: TextView
    private lateinit var textVisibility: TextView
    private lateinit var textSunrise: TextView
    private lateinit var textSunset: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initDataObservers()
    }

    private fun initDataObservers() {
        weatherViewModel.data.observe(this,
            object : Observer<WeatherRow> {
                override fun onChanged(t: WeatherRow?) {
                    if (t != null) {
                        setData(
                            t?.location,
                            t?.tempterature.toString(),
                            t?.windSpeed.toString(),
                            t?.humidity.toString(),
                            t?.visibility.toString(),
                            t?.sunsirse.toString(),
                            t?.sunset.toString()
                        )

                        containerInfo.visibility = View.VISIBLE
                    }
                }

            })

        weatherViewModel.loadingState.observe(this,
            object : Observer<LoadingState> {
                override fun onChanged(t: LoadingState?) {
                    when (t?.status) {
                        LoadingState.Status.FAILED -> {
                            Toast.makeText(
                                this@MainActivity,
                                resources.getString(R.string.place_not_found),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            })
    }

    fun setData(
        location: String,
        temperature: String,
        windSpeed: String,
        humidity: String,
        visibility: String,
        sunrise: String,
        sunset: String
    ) {
        textLocation.text = location
        textTemperature.text = "$temperature C"
        textWindSpeed.text = "$windSpeed mph"
        textHumidity.text = "$humidity %"
        textVisibility.text =
            resources.getString(ConvertVisibilityUtils.convertVisibilityToString(visibility.toInt()))
        textSunrise.text = dateFormatter.format(Date(sunrise.toLong() * 1000))
        textSunset.text = dateFormatter.format(Date(sunset.toLong() * 1000))
    }

    private fun initView() {
        containerInfo = findViewById(R.id.containerInfo)
        editZipCode = findViewById(R.id.editZipCode)
        btnGetWeather = findViewById(R.id.btnGetWeather)
        textLocation = findViewById(R.id.textLocation)
        textTemperature = findViewById(R.id.textTemperature)
        textWindSpeed = findViewById(R.id.textWindSpeed)
        textHumidity = findViewById(R.id.textHumidity)
        textVisibility = findViewById(R.id.textVisibility)
        textSunrise = findViewById(R.id.textSunrise)
        textSunset = findViewById(R.id.textSunset)
        btnGetWeather.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGetWeather -> {
                weatherViewModel.fetchData(editZipCode.text.toString())
            }
        }
    }
}