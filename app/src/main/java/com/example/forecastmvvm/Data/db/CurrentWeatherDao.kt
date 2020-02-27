package com.example.forecastmvvm.Data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forecastmvvm.Data.db.entity.CURRENT_WEATHER_ID
import com.example.forecastmvvm.Data.db.entity.CurrentWeatherEntry
import com.example.forecastmvvm.Data.db.unitlocalized.ImperialCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM CURRENT_WEATHER WHERE id = $CURRENT_WEATHER_ID")
    fun getWeather() : LiveData<ImperialCurrentWeatherEntry>
}