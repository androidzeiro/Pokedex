package br.com.raphael.pokedex.data.repository

import kotlinx.coroutines.flow.Flow
import br.com.raphael.pokedex.data.model.dto.Result
import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import br.com.raphael.pokedex.data.model.response.PokemonsResponse

interface PokemonRepository {
    suspend fun getPokemons(): Flow<Result<PokemonsResponse>>
    suspend fun getPokemonByName(name: String): Flow<Result<PokemonDetailResponse>>
}