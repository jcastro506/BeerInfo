package com.example.beerinfo.presentation.beer_detail


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
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
        //WOULD WRITE TEST TO ENSURE SAVEDSTATE IS RECEIVING VALUES
        savedStateHandle.get<String>("beer_id")?.let { id ->
            Log.d("NumID", "$id")
            getBeer(id.toInt())
        }
    }


    private fun getBeer(beerId : Int) {
        //WOULD TEST INVOCATION BEING HIT ON EACH
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
            //WOULD TEST CORRECT EMISSIONS
            emit(Resource.Loading<Beer>())
            val beer = repository.getBeerDetails(beerId)
            emit(Resource.Success<Beer>(beer.get(0)))
        } catch (e : HttpException) {
            emit(Resource.Error<Beer>(e.localizedMessage ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Beer>("Couldn't reach server. Bad internet"))
        }
    }

}