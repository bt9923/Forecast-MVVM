package com.example.forecastmvvm.Data.network

import androidx.lifecycle.LiveData
import com.example.forecastmvvm.Data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}