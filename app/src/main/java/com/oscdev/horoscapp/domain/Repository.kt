package com.oscdev.horoscapp.domain

import com.oscdev.horoscapp.domain.model.PredictionModel

interface Repository {

    suspend fun getPrediction(sign: String): PredictionModel?

}