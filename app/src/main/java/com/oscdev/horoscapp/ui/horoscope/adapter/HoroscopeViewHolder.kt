package com.oscdev.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.oscdev.horoscapp.databinding.ItemHoroscopeBinding
import com.oscdev.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscopeTitle.text =
            binding.tvHoroscopeTitle.context.getString(horoscopeInfo.nombre)
        binding.parent.setOnClickListener{
            startRotationAnimation(binding.ivHoroscope, newLambda = { (onItemSelected(horoscopeInfo)) })
        }
    }

    private fun startRotationAnimation(view: View, newLambda: () -> Unit){
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction{
                newLambda()
            }
            start()
        }
    }

}