package com.pokedex.models.move_item

import com.pokedex.models.common.SearchableByName
import kotlinx.serialization.Serializable

@Serializable
data class MoveItem(val id: Int, override val name: String, val type: MoveType) : SearchableByName