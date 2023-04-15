package com.example.weatherapp.data

import com.example.weatherapp.utils.PrefsUtil.date
import com.google.gson.annotations.SerializedName

data class NationalReaponse(
    @SerializedName("data")val data: Data
)
{   fun getStartTime(): String = data.timelines[0].startTime
    fun getTemperature(): Double = data.timelines[0].intervals[0].values.temperature
    fun getWeatherCode(): Int = data.timelines[0].intervals[0].values.weatherCode
    fun getHumidity(): Double = data.timelines[0].intervals[0].values.humidity
    fun getWindSpeed(): Double = data.timelines[0].intervals[0].values.windSpeed
    fun getCloudCover(): Double = data.timelines[0].intervals[0].values.cloudCover
}