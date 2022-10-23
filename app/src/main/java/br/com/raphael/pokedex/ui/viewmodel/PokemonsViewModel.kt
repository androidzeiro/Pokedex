package br.com.raphael.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raphael.pokedex.data.model.dto.State
import br.com.raphael.pokedex.data.repository.PokemonRepository
import br.com.raphael.pokedex.data.model.dto.Result
import br.com.raphael.pokedex.data.model.response.PokemonDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonsState = MutableLiveData<State<List<PokemonDetailResponse>>?>()
    val pokemonsState: LiveData<State<List<PokemonDetailResponse>>?> = _pokemonsState

    private var pokemons: ArrayList<PokemonDetailResponse> = ArrayList()

    init {
        getPokemons()
    }

    fun getPokemons() {
        pokemons = ArrayList()
        viewModelScope.launch {
            pokemonRepository.getPokemons().collect { result ->
                when (result) {
                    is Result.Success -> {
                        result.value.results.forEach { pokemonInfo ->
                            getPokemonByName(pokemonInfo.name)
                        }
                    }
                    is Result.Error -> {
                        _pokemonsState.postValue(State.Error(result.message))
                    }
                    is Result.NetworkError -> {
                        _pokemonsState.postValue(State.Error(result.message))
                    }
                }
            }
        }
    }

    private fun getPokemonByName(name: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonByName(name).collect { result ->
                when (result) {
                    is Result.Success -> {
                        if (pokemons.size != 19) {
                            pokemons.add(result.value)
                        } else {
                            pokemons.add(result.value)
                            _pokemonsState.postValue(State.Success(pokemons))
                        }
                    }
                    is Result.Error -> {
                        _pokemonsState.postValue(State.Error(result.message))
                    }
                    is Result.NetworkError -> {
                        _pokemonsState.postValue(State.Error(result.message))
                    }
                }
            }
        }
    }
}