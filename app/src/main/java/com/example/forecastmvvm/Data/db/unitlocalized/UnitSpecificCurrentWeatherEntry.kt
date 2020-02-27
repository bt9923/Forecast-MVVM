package com.example.forecastmvvm.Data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature : Double
//    val conditionIconUrl : String
    val windSpeed : Double
    val windDirection : String
    val precipitationVolume : Double
    val feelsLikeTemperature : Double
    val visibilityDistance : Double
}