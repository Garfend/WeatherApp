package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("timestep")val timestep: String,
    @SerializedName("endTime")val endTime: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("intervals") val intervals: List<Interval>
)

