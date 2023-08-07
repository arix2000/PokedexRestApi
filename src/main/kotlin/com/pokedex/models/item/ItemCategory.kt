package com.pokedex.models.item

import kotlinx.serialization.Serializable

@Serializable
data class ItemCategory(val name: String, val url: String)
