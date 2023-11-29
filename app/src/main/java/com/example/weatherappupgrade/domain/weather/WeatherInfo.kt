package com.example.weatherappupgrade.domain.weather

data class WeatherInfo(
    val WeatherDataPerDay: Map<Int, List<WeatherData>>,
    val CurrentWeatherData: WeatherData?
)
