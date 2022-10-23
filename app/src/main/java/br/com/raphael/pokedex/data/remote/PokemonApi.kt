package br.com.raphael.pokedex.data.remote

import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import br.com.raphael.pokedex.data.model.response.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): Response<PokemonsResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Response<PokemonDetailResponse>
}