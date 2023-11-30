package com.example.weatherappupgrade.presentation.modules.weather.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappupgrade.domain.location.LocationTracker
import com.example.weatherappupgrade.domain.respository.WeatherRepository
import com.example.weatherappupgrade.domain.util.Resource
import com.example.weatherappupgrade.presentation.modules.weather.state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set
    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            error = null,
                            weatherInfo = result.data
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message,
                            weatherInfo = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Could'not retrieve location. Make sure you have permission for it"
                )
            }
        }
    }

}