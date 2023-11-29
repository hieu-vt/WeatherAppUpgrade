package com.example.weatherappupgrade.presentation.state

import com.example.weatherappupgrade.domain.weather.WeatherInfo

data class WeatherState (
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)