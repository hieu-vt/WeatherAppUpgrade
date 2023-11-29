package com.example.weatherappupgrade.data.mapper

import com.example.weatherappupgrade.data.remote.WeatherDataDto
import com.example.weatherappupgrade.data.remote.WeatherDto
import com.example.weatherappupgrade.domain.weather.WeatherData
import com.example.weatherappupgrade.domain.weather.WeatherInfo
import com.example.weatherappupgrade.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class IndexWeatherData (
    val index : Int,
    val data: WeatherData
)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, t ->
        val temperature = temperature[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeed[index]
        val humidity = humidity[index]

        IndexWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(t, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius =  temperature,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )


    }.groupBy {
        it.index/24
    }.mapValues { it.value.map { it.data } }
}

fun WeatherDto.toWeatherDataInfo() : WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()

    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        WeatherDataPerDay = weatherDataMap,
        CurrentWeatherData = currentWeatherData,
    )
}