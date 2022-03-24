package com.example.beerinfo.presentation.beer_detail

import com.example.beerinfo.domain.model.Beer

data class BeerDetailsState(
    val isLoading : Boolean = false,
    val beer: Beer? = null,
    val error : String = ""
)
