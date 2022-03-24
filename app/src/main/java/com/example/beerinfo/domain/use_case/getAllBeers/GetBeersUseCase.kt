package com.example.beerinfo.domain.use_case.getAllBeers

import com.example.beerinfo.common.Resource
import com.example.beerinfo.domain.model.Beer
import com.example.beerinfo.domain.repository.BeerRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(
    private val repository : BeerRepository
) {

    operator fun invoke() : kotlinx.coroutines.flow.Flow<Resource<List<Beer>>> = flow {
        try {
            emit(Resource.Loading())
            val beers = repository.getAllBeers()
            emit(Resource.Success(beers))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error occured"))
        } catch (e:IOException) {
            emit(Resource.Error("Couldn't reach server. Bad internet"))
        }
    }

}