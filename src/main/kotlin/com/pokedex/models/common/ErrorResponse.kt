package com.pokedex.models.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val code: Int, val message: String)