package com.example.weatherappupgrade.domain.data

import com.squareup.moshi.Json

data class WeatherDto (
    @field:Json(name = "hourly")
    val weatherData : WeatherDataDto
)