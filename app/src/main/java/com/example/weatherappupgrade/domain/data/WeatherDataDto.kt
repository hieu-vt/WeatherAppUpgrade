package com.example.weatherappupgrade.domain.data

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time :List<String>,
    @field:Json(name = "temperature_2m")
    val temperature: List<Double>,
    @field:Json(name = "relative_humidity_2m")
    val humidity: List<Int>,
    @field:Json(name = "wind_speed_10m")
    val windSpeed: List<Double>
)
