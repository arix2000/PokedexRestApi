package com.pokedex.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(val hasNext: Boolean, val results: List<T>)
