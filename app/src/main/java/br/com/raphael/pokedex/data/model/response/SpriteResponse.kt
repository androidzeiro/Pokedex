package br.com.raphael.pokedex.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpriteResponse(
    val other: SpriteOtherResponse
) : Parcelable
