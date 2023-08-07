package com.pokedex.models.location_item

import com.pokedex.models.common.SearchableByName
import kotlinx.serialization.Serializable

@Serializable
data class LocationItem(val id: Int, override val name: String, val region: LocationRegion?): SearchableByName
