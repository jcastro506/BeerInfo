package com.example.beerinfo.domain.repository

import com.example.beerinfo.domain.model.Beer

interface BeerRepository {

    suspend fun getAllBeers(page : Int, perPage : Int) : List<Beer>

    suspend fun getBeerDetails(beerId : Int) : List<Beer>

}