package com.example.weatherappupgrade.data.repository

import com.example.weatherappupgrade.data.mapper.toWeatherDataInfo
import com.example.weatherappupgrade.data.remote.WeatherApi
import com.example.weatherappupgrade.domain.respository.WeatherRepository
import com.example.weatherappupgrade.domain.util.Resource
import com.example.weatherappupgrade.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api
                    .getWeather(lat = lat, long = long)
                    .toWeatherDataInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e?.message ?: "An unknown error occurred")
        }
    }
}