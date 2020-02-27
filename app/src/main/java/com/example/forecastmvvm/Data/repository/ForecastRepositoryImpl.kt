package com.example.forecastmvvm.Data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.forecastmvvm.Data.db.CurrentWeatherDao
import com.example.forecastmvvm.Data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.forecastmvvm.Data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.forecastmvvm.Data.network.WeatherNetworkDataSource
import com.example.forecastmvvm.Data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{
            //persist
            persistFetchedCurrentWeather(it)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<ImperialCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather : CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            "Pereira", Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime : ZonedDateTime) : Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val thirtyMinutes = ZonedDateTime.now().minusMinutes(30)
            lastFetchTime.isBefore(thirtyMinutes)
        }else
            false
    }
}