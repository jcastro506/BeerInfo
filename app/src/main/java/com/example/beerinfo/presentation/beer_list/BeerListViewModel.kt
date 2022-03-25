package com.example.beerinfo.presentation.beer_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


const val pageSize = 25
const val hardCodedSize = 80

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {

    private val _state = mutableStateOf(BeerListState())
    val state : State<BeerListState> = _state
    val page = mutableStateOf(1)
    var beerListScrollPosition = 0

    var beersList = mutableStateOf<List<Beer>>(listOf())
    var endReached = mutableStateOf(false)

    init {
        getBeers()
    }

    private fun getBeers() {
        invoke().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = BeerListState(beers = result.data ?: emptyList())
                    beersList.value = result.data!!
                   //page.value = page.value + 1
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

    //pagination logic/attempt

    fun nextPage() {
        viewModelScope.launch {
            //stop recompose from continuously triggering
            //lock logic below
            if((beerListScrollPosition + 1) >= page.value * pageSize) {
                _state.value.isLoading = true
                incrementPage()

                //fake delay for pagination
                delay(1000)

                if(page.value > 1) {
                    val result = repository.getAllBeers(page.value, hardCodedSize)
                    appendNewBeers(result)
                }
                _state.value.isLoading = false
            }
        }
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeBeerScrollPosition(position : Int) {
        beerListScrollPosition = position
    }

    fun appendNewBeers(beers : List<Beer>) {
        val current = ArrayList(beersList.value)
        current.addAll(beers)
        //_state.value = BeerListState(beers = current)
        beersList.value = current
    }

    //end of pagination logic


    private fun invoke() : kotlinx.coroutines.flow.Flow<Resource<List<Beer>>> = flow {
        try {
            emit(Resource.Loading<List<Beer>>())
            val beers = repository.getAllBeers(page = page.value, hardCodedSize )
            beersList.value = repository.getAllBeers(page = page.value, hardCodedSize)
            emit(Resource.Success<List<Beer>>(beers))
        } catch (e : HttpException) {
            emit(Resource.Error<List<Beer>>(e.localizedMessage ?: "Unknown error occured"))
        } catch (e:IOException) {
            emit(Resource.Error<List<Beer>>("Couldn't reach server. Bad internet"))
        }
    }



}