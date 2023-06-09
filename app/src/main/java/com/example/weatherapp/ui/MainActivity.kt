package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.adapters.ClotheAdpt
import com.example.weatherapp.adapters.WeatherAdpt
import com.example.weatherapp.data.*
import com.example.weatherapp.data.api.NationalReaponse
import okhttp3.*
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.data.utils.PrefsUtil
import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val dataSource by lazy { DataSource() }
    lateinit var binding: ActivityMainBinding
    private  val logInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSet()
    }

    private fun onSet(){
        prefsUtils()
        makeRequestUsingOKHttp()
    }

    private fun prefsUtils(){
        PrefsUtil.initPrefsUtil(this)
    }

    private fun weatherCondition(currentCode:Int){
        binding.textViewWeatherCondition.text = WeatherCodes.weatherRange[currentCode].toString()
        val selectedWindAdpt = when (currentCode)
        {
            in 0..1100 ->  WeatherAdpt(dataSource.sunnyIcon)
            in 1101..2100 -> WeatherAdpt(dataSource.cloudyIcon)
            in 4000..4201 -> WeatherAdpt(dataSource.rainIcon)
            in 5000..5101 -> WeatherAdpt(dataSource.snowIcon)
            in 6000..7102 -> WeatherAdpt(dataSource.freezeIcon)
            else -> WeatherAdpt(dataSource.thunderIcon)
        }
        binding.rainImageLargeCard.adapter = selectedWindAdpt
    }

    private fun updateRecycler(currentCode: Int,
                               temperature: Double,
                               currentDay:String) {
         if (currentDay == PrefsUtil.date) {

             val selectedClothAdapter = if (currentCode >= 4000) {
                 when (currentCode) {
                     in 4000..4201 -> {
                         ClotheAdpt(dataSource.rainClothe.random() as List<IdImage>)
                     }
                     else -> {
                         ClotheAdpt(dataSource.rainClothe.random() as List<IdImage>)
                     }
                 }
             } else {
                 when (temperature) {
                     in 10.0..25.0 -> {
                         ClotheAdpt(dataSource.winterClothe.random() as List<IdImage>
                         )
                     }
                     in 26.0..40.0 -> {
                         ClotheAdpt(dataSource.sunnyClothes.random() as List<IdImage>)
                     }
                     else -> {
                         ClotheAdpt(dataSource.sunnyClothes.random() as List<IdImage>)
                     }
                 }
             }

             binding.clothes.adapter = selectedClothAdapter
         }

    }

    fun getCurrentDate(): String = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())
    fun getCurrentDay(): String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
    fun getCurrentTime(): String = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

    private fun updateRecyclerUI(currentCode: Int,tempreture:Double, currentDate: String)
    {
        weatherCondition(currentCode)
        updateRecycler(currentCode,tempreture ,currentDate)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(temp: Double,
                         hum:Double,
                         win:Double,
                         clou: Float,
                         time: String,
                         date: String)
    {
        binding.time.text = time
        binding.date.text = date
        binding.textViewHumidity.text = "$hum %"
        binding.textViewCloudCoverage.text = "$clou %"
        binding.textViewWindSpeed.text = "$win M/S"
        binding.textViewTemp.text = "$temp °"
    }

    private fun makeRequestUsingOKHttp() {
        val url = HttpUrl.Builder().scheme("https")
            .host("api.tomorrow.io")
            .addPathSegment("v4")
            .addPathSegment("timelines")
            .addQueryParameter("location","30.033333, 31.233334")
            .addQueryParameter("fields","temperature,humidity,windSpeed,cloudCover,weatherCode")
            .addQueryParameter("timesteps","current")
            .addQueryParameter("units","metric")
            .addQueryParameter("apikey","QioSZUu3rQ7xP0QIrsWTIIdrvuRveGoJ")
            .build()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: java.io.IOException) {

            }
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                response.body.string().let {jsonString ->
                        val result = Gson().fromJson(jsonString, NationalReaponse::class.java)
                    val temp = result.getTemperature()
                    val hum = result.getHumidity()
                    val clou = result.getCloudCover()
                    val win = result.getWindSpeed()
                    val wCode = result.getWeatherCode().toString()
                    val date = getCurrentDate()
                    val time = getCurrentTime()
                    val day = getCurrentDay()

                    runOnUiThread{
                        updateUI(temp , hum, win ,clou, time, date)
                        updateRecyclerUI(wCode.toInt(), temp , day)
                        PrefsUtil.date = day
                    }
                }
            }

        })

    }

}