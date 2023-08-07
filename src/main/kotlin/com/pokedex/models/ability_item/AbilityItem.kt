package com.pokedex.models.ability_item

import com.pokedex.models.common.SearchableByName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityItem(val id: Int, override val name: String) : SearchableByName
