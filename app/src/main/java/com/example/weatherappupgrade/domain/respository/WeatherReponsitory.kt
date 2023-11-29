package com.example.weatherappupgrade.domain.respository

import com.example.weatherappupgrade.domain.util.Resource
import com.example.weatherappupgrade.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}