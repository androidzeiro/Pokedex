package br.com.raphael.pokedex.data.model.dto

sealed class State<out T> {
    data class Success<out T>(val value: T? = null) : State<T>()
    data class Error(val message: String) : State<Nothing>()
}