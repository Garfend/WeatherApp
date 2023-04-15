package com.example.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.data.*

import okhttp3.*
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.utils.PrefsUtil
import com.example.weatherapp.utils.PrefsUtil.date
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
            in 1101..2100 ->WeatherAdpt(dataSource.cloudyIcon)
            in 4000..4201 ->WeatherAdpt(dataSource.rainIcon)
            in 5000..5101 -> WeatherAdpt(dataSource.snowIcon)
            in 6000..7102 -> WeatherAdpt(dataSource.freezeIcon)
            else -> WeatherAdpt(dataSource.thunderIcon)
        }
        binding.rainImageLargeCard.adapter = selectedWindAdpt
    }

    private fun updateRecycler(currentCode: Int,currentDate:String) {
        if (currentDate == date) {
            val selectedClothAdapter = when (currentCode) {
                in 0..2100 -> {
                    if (PrefsUtil.clothe == 1) {
                        ClotheAdpt(dataSource.sunnyshirt)
                    } else {
                        PrefsUtil.clothe = 1
                        ClotheAdpt(dataSource.sunnyCloth)
                    }
                }
                in 4000..4201 -> {
                    if (PrefsUtil.clothe == 2) {
                        ClotheAdpt(dataSource.coolWeatherCloth)
                    } else {
                        PrefsUtil.clothe = 2
                        ClotheAdpt(dataSource.rainCoats)
                    }
                }
                else -> {
                    if (PrefsUtil.clothe == 3) {
                        ClotheAdpt(dataSource.coolWeatherCloth)
                    } else {
                        PrefsUtil.clothe = 3
                        ClotheAdpt(dataSource.classicJacket)
                    }
                }
            }
            binding.clothes.adapter = selectedClothAdapter
        }
    }

    fun getCurrentDate(): String = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())
    fun getCurrentTime(): String = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

    private fun updateRecyclerUI(currentCode: Int, currentDate: String)
    {
        weatherCondition(currentCode)
        updateRecycler(currentCode, currentDate)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(temp: Double,
                         hum:Double,
                         win:Double,
                         clou: Double,
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
            .addQueryParameter("apikey","jmYQASoTkp4PBlLLbQ9E3AhJREunMSe2")
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
                    val clou = result.getWindSpeed()
                    val win = result.getCloudCover()
                    val wCode = result.getWeatherCode().toString()
                    val date = getCurrentDate()
                    val time = getCurrentTime()

                    runOnUiThread{
                        updateUI(temp,hum, win ,clou, time, date)
                        updateRecyclerUI(wCode.toInt(), date)
                        PrefsUtil.date = date
                    }
                }
            }

        })

    }

}





