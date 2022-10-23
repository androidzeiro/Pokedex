package br.com.raphael.pokedex.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailResponse(
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val types: List<TypeResponse>,
    val abilities: List<AbilityResponse>,
    val sprites: SpriteResponse
) : Parcelable