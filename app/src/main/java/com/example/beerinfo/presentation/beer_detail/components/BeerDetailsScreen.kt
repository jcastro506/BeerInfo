package com.example.beerinfo.presentation.beer_detail.components

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.rememberImagePainter
import com.example.beerinfo.data.remote.dto.Ingredients
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.presentation.beer_detail.BeerDetailViewModel

@Composable
fun BeerDetailsScreen(
    viewModel : BeerDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    state.beer?.let { beer ->
            BeerDetails(beer = beer)
    }
}


@Composable
fun BeerDetails(
    beer: Beer
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier) {
                    Image(painter = rememberImagePainter(beer.imageUrl), contentDescription = beer.name)
                    Text(text = beer.name, textAlign = TextAlign.Center, fontSize = 24.sp, modifier = Modifier
                        .padding(8.dp))
                    Text(text = "IBU: ${beer.ibu.toString()}", fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                        .padding(8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column(modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState(), enabled = true)) {
            Row(modifier = Modifier) {
                Text(text = "Description", fontSize = 20.sp, fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(), softWrap = true)
            }
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = beer.description, fontSize = 13.sp, )
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row(modifier = Modifier) {
                Text(text = "Ingredients", fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
            Row(modifier = Modifier.padding(6.dp)) {
                    Text(text = "Malt: ", fontSize = 14.sp)
                    IngredientsMalt(ingredients = beer.ingredients)
            }
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Hops: ", fontSize = 14.sp)
                IngredientsHops(ingredients = beer.ingredients)
            }
            Row(modifier = Modifier.padding(6.dp)) {
                Text(text = "Yeast:  ${beer.ingredients.yeast}", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row(modifier = Modifier) {
                Text(text = "Food Pairings", fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
            Row(modifier = Modifier.padding(6.dp)) {
                FoodPairings(pairings = beer.foodPairing)
            }
        }
    }
}


@Composable
fun IngredientsMalt(
    ingredients: Ingredients
) {
    Column(modifier = Modifier
        .wrapContentSize(align = Alignment.Center)
        .padding(start = 4.dp)) {
            ingredients.malt.forEach { ingredient ->
                Text(text = ingredient.name, fontSize = 13.sp)
            }
        }
}

@Composable
fun IngredientsHops(
    ingredients: Ingredients
) {
    Column(modifier = Modifier
        .wrapContentSize(align = Alignment.Center)
        .padding(start = 4.dp)) {
        ingredients.hops.forEach { ingredient ->
            Text(text = ingredient.name, fontSize = 13.sp)
        }
    }
}

@Composable
fun FoodPairings(
    pairings : List<*>
) {
    Column(modifier = Modifier
        .wrapContentSize(align = Alignment.Center)
        .padding(start = 4.dp)) {
        pairings.forEach { pair ->
            Text(text = "- $pair", fontSize = 13.sp)
        }
    }
}
