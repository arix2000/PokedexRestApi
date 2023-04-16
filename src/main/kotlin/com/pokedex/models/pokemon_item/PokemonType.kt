package com.pokedex.models.pokemon_item

import kotlinx.serialization.Serializable

@Serializable
data class PokemonType(val slot: Int, val name: String)
