package com.example.beerinfo.presentation.beer_list.components


import androidx.compose.animation.fadeIn
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
import androidx.compose.material.*
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
import com.example.beerinfo.presentation.beer_list.pageSize

@Composable
fun BeerListScreen(
    viewModel: BeerListViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.state.value
    val allBeers = state.beers
    val beersList = viewModel.beersList.value
    val page = viewModel.page.value

    androidx.compose.material.Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            //Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_sports_bar_24),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            LazyColumn(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)) {
                    itemsIndexed(items = beersList) { index, beer ->
                        viewModel.onChangeBeerScrollPosition(index)
                        if ((index + 1) >= (page * pageSize )) {
                            viewModel.nextPage()
                        }
                    BeerCard(beer = beer, onItemClick = {
                        navController.navigate("beer_details_screen/${beer.id}")
                    })
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            if (state.isLoading) {
                CircularProgressIndicator(color = Color.Green)
            }
        }
    }
}


@Composable
fun BeerCard(
    beer: Beer,
    onItemClick: (Beer) -> Unit
) {
    Box(contentAlignment = Center , modifier = Modifier
        .shadow(5.dp)
        .clip(RoundedCornerShape(10.dp))
        .aspectRatio(1f)
        .clickable { onItemClick(beer) }) {
        
        Column() {
            Image(painter = rememberImagePainter(beer.imageUrl), contentDescription = beer.name, 
            modifier = Modifier
                .size(210.dp)
                .align(CenterHorizontally))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = beer.name, fontSize = 20.sp, textAlign = TextAlign.Center, color = Color.Black, modifier = Modifier.fillMaxWidth())
        }
    }
}