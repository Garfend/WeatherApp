package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.Values
import com.google.gson.annotations.SerializedName

data class Interval(
    @SerializedName("startTime")val startTime: String,
    @SerializedName("values")val values: Values
)
