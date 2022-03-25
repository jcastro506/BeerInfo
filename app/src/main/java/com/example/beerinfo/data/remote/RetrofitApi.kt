package com.example.beerinfo.data.remote

import com.example.beerinfo.data.remote.dto.BeerListResponse
import com.example.beerinfo.domain.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("beers")
    suspend fun getAllBeers(
        @Query("page") page : Int,
        @Query("per_page") per_page : Int
    ) : List<Beer>

    @GET("beers/{beer_id}")
    suspend fun getBeerDetails(
        @Path("beer_id") beer_id : Int
    ) : List<Beer>
}