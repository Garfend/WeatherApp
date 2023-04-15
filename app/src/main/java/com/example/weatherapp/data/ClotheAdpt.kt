package com.example.weatherapp.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemImageClotheBinding

class ClotheAdpt (private val ClothList:List<IdImage>) :RecyclerView.Adapter<ClotheAdpt.ClotheViewHolder>(){

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClotheAdpt.ClotheViewHolder {
        val bind=ItemImageClotheBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClotheViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ClotheAdpt.ClotheViewHolder, position: Int) =holder.getImage(ClothList[position])

    override fun getItemCount(): Int = ClothList.size

    class ClotheViewHolder(private val binding: ItemImageClotheBinding) : RecyclerView.ViewHolder(binding.root) {
        fun getImage(cloth: IdImage) {
            binding.clothesDrip.setImageResource(cloth.imageSrc)
        }
    }
}