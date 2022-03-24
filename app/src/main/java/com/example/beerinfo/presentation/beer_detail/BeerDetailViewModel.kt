package com.example.beerinfo.presentation.beer_detail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import com.example.beerinfo.presentation.beer_list.BeerListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    private val repository: BeerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(BeerDetailsState())
    val state : State<BeerDetailsState> = _state

    init {
        var id = savedStateHandle.get<Int>("beer_id")?.let { beerId ->
            getBeer(beerId)
        }
    }

    private fun getBeer(beerId : Int) {
        invoke(beerId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = BeerDetailsState(beer = result.data)
                }
                is Resource.Error -> {
                    _state.value = BeerDetailsState(
                        error =  result.message ?: "Unknown error"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BeerDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun invoke(beerId : Int) : Flow<Resource<Beer>> = flow {
        try {
            emit(Resource.Loading<Beer>())
            val beer = repository.getBeerDetails(beerId)
            emit(Resource.Success<Beer>(beer))
        } catch (e : HttpException) {
            emit(Resource.Error<Beer>(e.localizedMessage ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Beer>("Couldn't reach server. Bad internet"))
        }
    }

}