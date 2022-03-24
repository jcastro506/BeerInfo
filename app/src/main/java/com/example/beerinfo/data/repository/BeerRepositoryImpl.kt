package com.example.beerinfo.data.repository

import com.example.beerinfo.data.remote.RetrofitApi
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val api : RetrofitApi
) : BeerRepository {

    override suspend fun getAllBeers(): List<Beer> {
        TODO("Not yet implemented")
    }

    override suspend fun getBeerDetails(beerId: Int): Beer {
        TODO("Not yet implemented")
    }

}