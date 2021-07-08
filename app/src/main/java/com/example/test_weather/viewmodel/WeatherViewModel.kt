package com.example.test_weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_weather.helper.LoadingState
import com.example.test_weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()

    val loadingState: LiveData<LoadingState>get() = _loadingState

    val data = weatherRepository.data

    fun fetchData(zipCode: String){
        viewModelScope.launch {
            try{
                _loadingState.value = LoadingState.LOADING
                weatherRepository.refresh(zipCode)
                _loadingState.value = LoadingState.LOADED
            }catch (e : Exception){
                _loadingState.value = LoadingState.error(e.message!!)
            }
        }
    }
}