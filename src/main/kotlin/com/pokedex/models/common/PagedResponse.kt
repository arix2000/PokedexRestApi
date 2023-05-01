package com.pokedex.models.common

data class PagedResponse<T>(val count: Int, val previous: String, val next:String, val results: List<T> )