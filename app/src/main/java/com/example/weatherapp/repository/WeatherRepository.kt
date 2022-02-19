package com.example.weatherapp.repository

import com.example.weatherapp.network.NetworkHelper

class WeatherRepository {
    suspend fun fetchWeatherByCity(cityName: String) =
        NetworkHelper.getWeatherService().fetchWeatherByCityName(cityName)


    suspend fun fetchFiveDayWeather(cityName: String) =
        NetworkHelper.getWeatherService().fetchFiveDayWeather(cityName)
}
