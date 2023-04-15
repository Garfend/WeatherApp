package com.example.weatherapp.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.databinding.ItemImageWindBinding

class WeatherAdpt (private val windList:List<IdImage>) : RecyclerView.Adapter<WeatherAdpt.WindViewHolder>(){

        @SuppressLint("SuspiciousIndentation")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindViewHolder {
            val bind= ItemImageWindBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return WindViewHolder(bind)
        }

        override fun onBindViewHolder(holder: WindViewHolder, position: Int) =holder.getImage(windList[position])

        override fun getItemCount(): Int = windList.size

        class WindViewHolder(private val binding: ItemImageWindBinding) : RecyclerView.ViewHolder(binding.root) {
            fun getImage(cloth:IdImage){
                binding.rainImageLargeCard.setImageResource(cloth.imageSrc)
            }
        }
    }