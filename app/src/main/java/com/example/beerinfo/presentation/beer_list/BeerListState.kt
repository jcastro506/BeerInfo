package com.example.beerinfo.presentation.beer_list

import com.example.beerinfo.domain.model.Beer

data class BeerListState(
    var isLoading : Boolean = false,
    var beers: List<Beer> = emptyList(),
    val error : String = ""
)
