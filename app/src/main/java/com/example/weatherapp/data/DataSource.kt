package com.example.weatherapp.data

import com.example.weatherapp.R

class DataSource {
    val classicJacket:List<IdImage> = listOf(
        IdImage(R.drawable.classicjacket),
        IdImage(R.drawable.classicpant),
        IdImage(R.drawable.gentleboot))
    val rainCoats:List<IdImage> = listOf(
        IdImage(R.drawable.coat),
        IdImage(R.drawable.rainnoot),)
    val sunnyCloth:List<IdImage> = listOf(
        IdImage(R.drawable.teshirt),
        IdImage(R.drawable.pants),
        IdImage(R.drawable.boot))
    val coolWeatherCloth:List<IdImage> = listOf(
        IdImage(R.drawable.shirt1),
        IdImage(R.drawable.jeans),
        IdImage(R.drawable.boot))
    val sunnyshirt:List<IdImage> = listOf(
        IdImage(R.drawable.sunnyshirt),
        IdImage(R.drawable.jeans),
        IdImage(R.drawable.gentleboot))





    val rainIcon:List<IdImage> = listOf(
        IdImage(R.drawable.rain_icon))
    val sunnyIcon:List<IdImage> = listOf(
        IdImage(R.drawable.sun_icon))
    val windIcon:List<IdImage> = listOf(
        IdImage(R.drawable.wind_icon))
    val snowIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_snow))
    val cloudyIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_cloudy))
    val freezeIcon:List<IdImage> = listOf(
        IdImage(R.drawable.freeze_icon))
    val thunderIcon:List<IdImage> = listOf(
        IdImage(R.drawable.thunder_icon))
}