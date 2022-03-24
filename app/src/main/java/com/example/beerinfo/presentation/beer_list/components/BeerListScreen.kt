package com.example.beerinfo.presentation.beer_list.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.beerinfo.R
import com.example.beerinfo.data.remote.dto.BeerListResponseItem
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.presentation.beer_list.BeerListViewModel

@Composable
fun BeerListScreen(
    viewModel: BeerListViewModel,
    navController: NavController
) {

    val state = viewModel.state.value
    val allBeers = state.beers

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.beers) { beer ->
                BeerRow(beer = beer, navController = navController, onItemClick = {
                    navController.navigate("beer_details_screen/${beer.id}")
                })
            }
        }
    }
}


@Composable
fun BeerRow(
    beer: Beer,
    navController: NavController,
    onItemClick: (Beer) -> Unit
) {
    Text(text = beer.name, modifier = Modifier.clickable {onItemClick(beer)})
}
