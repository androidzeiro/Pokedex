package br.com.raphael.pokedex.data.repository

import android.content.Context
import br.com.raphael.pokedex.data.model.dto.Result
import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import br.com.raphael.pokedex.data.model.response.PokemonsResponse
import br.com.raphael.pokedex.data.remote.FlowApiCall
import br.com.raphael.pokedex.data.remote.PokemonApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemons(): Flow<Result<PokemonsResponse>> = flow {
        FlowApiCall.getResult(context, this) {
            pokemonApi.getPokemons()
        }
    }

    override suspend fun getPokemonByName(name: String): Flow<Result<PokemonDetailResponse>> = flow {
        FlowApiCall.getResult(context, this) {
            pokemonApi.getPokemonByName(name)
        }
    }
}