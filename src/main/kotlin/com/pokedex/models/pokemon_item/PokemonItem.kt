package com.pokedex.models.pokemon_item

import com.pokedex.models.common.SearchableByName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItem(
    val id: Int,
    override val name: String,
    val types: List<PokemonType>,
    @SerialName("image_url") val imageUrl: String?
) : SearchableByName
