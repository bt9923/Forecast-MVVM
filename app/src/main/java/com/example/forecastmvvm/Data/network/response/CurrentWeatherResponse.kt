package com.example.forecastmvvm.Data.network.response


import com.example.forecastmvvm.Data.db.entity.CurrentWeatherEntry
import com.example.forecastmvvm.Data.db.entity.Location
import com.example.forecastmvvm.Data.db.entity.Request
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)