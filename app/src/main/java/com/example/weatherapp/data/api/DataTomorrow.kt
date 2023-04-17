package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.Timeline
import com.google.gson.annotations.SerializedName

data class DataTomorrow(
    @SerializedName("timelines") val timelines: List<Timeline>

)


