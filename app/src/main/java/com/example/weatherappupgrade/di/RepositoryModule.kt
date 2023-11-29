package com.example.weatherappupgrade.di

import com.example.weatherappupgrade.data.location.DefaultLocationTracker
import com.example.weatherappupgrade.data.repository.WeatherRepositoryImpl
import com.example.weatherappupgrade.domain.location.LocationTracker
import com.example.weatherappupgrade.domain.respository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}