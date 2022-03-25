package com.example.beerinfo.data.repository

import com.example.beerinfo.data.remote.RetrofitApi
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val api : RetrofitApi
) : BeerRepository {

    override suspend fun getAllBeers(page : Int, perPage : Int): List<Beer> {
        return api.getAllBeers(page = page, per_page = perPage)
    }

    override suspend fun getBeerDetails(beerId: Int): List<Beer> {
        return api.getBeerDetails(beerId)
    }

}