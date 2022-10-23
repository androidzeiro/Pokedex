package br.com.raphael.pokedex.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpriteOtherResponse(
    @Json(name = "official-artwork")
    val officialArtwork: OfficialArtworkResponse
) : Parcelable
