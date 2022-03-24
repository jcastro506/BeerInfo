package com.example.beerinfo.presentation.beer_list

import androidx.lifecycle.ViewModel
import com.example.beerinfo.domain.use_case.getAllBeers.GetBeersUseCase
import com.example.beerinfo.domain.use_case.getBeer.GetBeerUseCase
import javax.inject.Inject

class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
) : ViewModel() {



}