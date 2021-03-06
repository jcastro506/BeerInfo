package com.example.beerinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.beerinfo.presentation.beer_detail.components.BeerDetailsScreen
import com.example.beerinfo.presentation.beer_list.BeerListViewModel
import com.example.beerinfo.presentation.beer_list.components.BeerListScreen
import com.example.beerinfo.ui.theme.BeerInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeerInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "beer_list_screen" ) {
                        composable(
                            route = "beer_list_screen"
                        ) {
                            BeerListScreen(navController = navController)
                        }
                        composable(
                            route = "beer_details_screen/{beer_id}"
                        ) {
                            BeerDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}