package com.example.beerinfo.presentation.beer_detail.components

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.rememberImagePainter
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.presentation.beer_detail.BeerDetailViewModel

@Composable
fun BeerDetailsScreen(
    viewModel : BeerDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
        state.beer?.let { beer ->
            Text(beer.name)
        }
}

