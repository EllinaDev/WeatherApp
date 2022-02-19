package com.example.weatherapp.network

import com.example.weatherapp.WeatherByCity
import com.example.weatherapp.network.model.forecast.FiveDayForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun fetchWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") appId: String = "2ddd529eee7809426f2368dca1ec6505",
        @Query("units") units: String = "metric"
    ): WeatherByCity

    @GET("forecast")
    suspend fun fetchFiveDayWeather(
        @Query("q") cityName: String,
        @Query("appid") appId: String = "2ddd529eee7809426f2368dca1ec6505",
        @Query("units") units: String = "metric"
    ): FiveDayForecast
}