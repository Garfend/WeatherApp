package com.example.weatherapp.data.api

import com.google.gson.annotations.SerializedName

data class Values(
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("weatherCode") val weatherCode: Int,
    @SerializedName("humidity")	val humidity: Double,
    @SerializedName("windSpeed")	val windSpeed: Double,
    @SerializedName("cloudCover")	val cloudCover: Double
)
