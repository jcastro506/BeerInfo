package com.example.beerinfo.presentation.beer_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import com.example.beerinfo.domain.use_case.getAllBeers.GetBeersUseCase
import com.example.beerinfo.domain.use_case.getBeer.GetBeerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {

    private val _state = mutableStateOf(BeerListState())
    val state : State<BeerListState> = _state

    init {
        getBeers()
    }

    private fun getBeers() {
        invoke().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = BeerListState(beers = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BeerListState(
                        error =  result.message ?: "Unknown error"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BeerListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun invoke() : kotlinx.coroutines.flow.Flow<Resource<List<Beer>>> = flow {
        try {
            emit(Resource.Loading<List<Beer>>())
            val beers = repository.getAllBeers()
            emit(Resource.Success<List<Beer>>(beers))
        } catch (e : HttpException) {
            emit(Resource.Error<List<Beer>>(e.localizedMessage ?: "Unknown error occured"))
        } catch (e:IOException) {
            emit(Resource.Error<List<Beer>>("Couldn't reach server. Bad internet"))
        }
    }
}