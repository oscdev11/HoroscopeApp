package com.oscdev.horoscapp.data

import android.util.Log
import com.oscdev.horoscapp.data.network.HoroscopeApiService
import com.oscdev.horoscapp.domain.Repository
import com.oscdev.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("TAG", "getPrediction: Ha ocurrido un error ${it.message} ") }
        return null
    }

}