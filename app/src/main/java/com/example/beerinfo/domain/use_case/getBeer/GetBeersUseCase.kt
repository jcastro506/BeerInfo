package com.example.beerinfo.domain.use_case.getBeer

import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(
    private val repository : BeerRepository
) {

    operator fun invoke(beerId : Int) : Flow<Resource<Beer>> = flow {
        try {
            emit(Resource.Loading())
            val beers = repository.getBeerDetails(beerId)
            emit(Resource.Success(beers))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Bad internet"))
        }
    }

}