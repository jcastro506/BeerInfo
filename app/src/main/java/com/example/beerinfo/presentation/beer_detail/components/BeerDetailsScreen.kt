package com.example.beerinfo.presentation.beer_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.presentation.beer_detail.BeerDetailViewModel

@Composable
fun BeerDetailsScreen(
    viewModel : BeerDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
        state.beer?.let { 
            LazyColumn() {
                item {
                    Text(text = it.name)
                    Text(text = it.description)
                    it.foodPairing.map { 
                        Text(text = it)
                    }
                }
            }
        }
    }
}