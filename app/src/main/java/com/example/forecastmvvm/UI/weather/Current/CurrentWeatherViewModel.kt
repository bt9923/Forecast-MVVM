package com.example.forecastmvvm.UI.weather.Current

import androidx.lifecycle.ViewModel
import com.example.forecastmvvm.Data.repository.ForecastRepository
import com.example.forecastmvvm.Internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()

    }
}
