package com.example.weatherappupgrade.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast?hourly=temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m")
    suspend fun getWeather(
        @Query("latitude") lat:Double,
        @Query("longitude") long:Double,
    ) : WeatherDto
}