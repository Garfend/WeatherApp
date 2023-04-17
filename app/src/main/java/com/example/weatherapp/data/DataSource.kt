package com.example.weatherapp.data

import com.example.weatherapp.R

class DataSource {
    val classicJacket:List<IdImage> = listOf(
        IdImage(R.drawable.classicjacket),
        IdImage(R.drawable.classicpant),
        IdImage(R.drawable.gentleboot))

    val classicJacket1:List<IdImage> = listOf(
        IdImage(R.drawable.jacket1),
        IdImage(R.drawable.classicpant),
        IdImage(R.drawable.gentleboot))

    val rainCoats:List<IdImage> = listOf(
        IdImage(R.drawable.coat),
        IdImage(R.drawable.rainnoot),)

    val coolWeatherCloth:List<IdImage> = listOf(
        IdImage(R.drawable.shirt1),
        IdImage(R.drawable.jeans),
        IdImage(R.drawable.boot))

    val sunnyshirt:List<IdImage> = listOf(
        IdImage(R.drawable.sunnyshirt),
        IdImage(R.drawable.jeans),
        IdImage(R.drawable.gentleboot))

    val sunnyCloth:List<IdImage> = listOf(
        IdImage(R.drawable.teshirt),
        IdImage(R.drawable.pants),
        IdImage(R.drawable.boot))

    val sunnyClothes: List<IdImage> = listOf(sunnyshirt, sunnyCloth, coolWeatherCloth) as List<IdImage>
    val rainClothe:List<IdImage> = listOf(rainCoats, rainCoats) as List<IdImage>
    val winterClothe:List<IdImage> = listOf(classicJacket, classicJacket1) as List<IdImage>








    val rainIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_rain))
    val sunnyIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_sun))
    val windIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_wind))
    val snowIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_snow))
    val cloudyIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_cloud))
    val freezeIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_freeze))
    val thunderIcon:List<IdImage> = listOf(
        IdImage(R.drawable.icon_thunder))
}