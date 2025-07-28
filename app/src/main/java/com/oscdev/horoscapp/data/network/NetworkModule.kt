package com.oscdev.horoscapp.data.network

import com.oscdev.horoscapp.BuildConfig.BASE_URL
import com.oscdev.horoscapp.core.interceptors.AuthInterceptor
import com.oscdev.horoscapp.data.RepositoryImpl
import com.oscdev.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module // los módulos son para hacer cosas que están fuera de lo común
@InstallIn(SingletonComponent::class) //alcance, con "SingletonComponent" todo mundo puede acceder a esto
object NetworkModule {

    @Provides // va a proveer retrofit, aquí se va a configurar para que pueda devolver un retrofit
    @Singleton // permite tener una única instancia de la clase
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }
}