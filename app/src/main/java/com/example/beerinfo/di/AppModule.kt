package com.example.beerinfo.di

import com.example.beerinfo.common.Constants.BASE_URL
import com.example.beerinfo.data.remote.RetrofitApi
import com.example.beerinfo.data.repository.BeerRepositoryImpl
import com.example.beerinfo.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerApi() : RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerRepository(api : RetrofitApi) : BeerRepository {
        return BeerRepositoryImpl(api)
    }

}