package com.example.forecastmvvm.Data.repository

import androidx.lifecycle.LiveData
import com.example.forecastmvvm.Data.db.unitlocalized.ImperialCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather() : LiveData<ImperialCurrentWeatherEntry>
}