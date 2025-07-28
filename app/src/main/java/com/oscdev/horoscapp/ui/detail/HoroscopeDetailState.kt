package com.oscdev.horoscapp.ui.detail

import com.oscdev.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {

    data object Loading: HoroscopeDetailState()
    data class Error(val error: String): HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()

}