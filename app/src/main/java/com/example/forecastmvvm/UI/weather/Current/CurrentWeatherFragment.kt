package com.example.forecastmvvm.UI.weather.Current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.forecastmvvm.Data.network.ApixuWeatherApiService
import com.example.forecastmvvm.Data.network.ConnectivityInterceptorImpl
import com.example.forecastmvvm.Data.network.WeatherNetworkDataSource
import com.example.forecastmvvm.Data.network.WeatherNetworkDataSourceImpl

import com.example.forecastmvvm.R
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService =
            ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
            textView.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
//            val currentWeatherResponse = apiService.getCurrentWeather("Pereira").await()
//            textView.text = currentWeatherResponse.toString()

            weatherNetworkDataSource.fetchCurrentWeather("Pereira", "es")
        }
    }

}
