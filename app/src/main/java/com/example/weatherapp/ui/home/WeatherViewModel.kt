package com.example.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.WeatherByCity
import com.example.weatherapp.network.model.forecast.FiveDayForecast
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    val weather: MutableLiveData<WeatherByCity> = MutableLiveData()
    val forecast: MutableLiveData<FiveDayForecast> = MutableLiveData()
    private val repository: WeatherRepository = WeatherRepository()

    fun fetchWeatherByCity(cityName: String) {
        viewModelScope.launch {
            try {
                val response: WeatherByCity = repository.fetchWeatherByCity(cityName)
                weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchFiveDayWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val response: FiveDayForecast = repository.fetchFiveDayWeather(cityName)
                forecast.value = response
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun metresToKilometres(metres: Int): Int {
        return metres.div(1000)
    }

}