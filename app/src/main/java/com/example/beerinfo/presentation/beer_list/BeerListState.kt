package com.example.beerinfo.presentation.beer_list

import com.example.beerinfo.domain.model.Beer

data class BeerListState(
    val isLoading : Boolean = false,
    val beers: List<Beer> = emptyList(),
    val error : String = ""
)
