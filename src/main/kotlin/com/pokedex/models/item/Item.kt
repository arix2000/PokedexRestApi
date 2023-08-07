package com.pokedex.models.item

import com.pokedex.models.common.SearchableByName
import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: Int, override val name: String, val category: ItemCategory) : SearchableByName
