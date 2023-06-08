package com.pokedex.routes

import com.pokedex.extensions.filterAndSortListBy
import com.pokedex.models.common.ErrorResponse
import com.pokedex.models.pokemon_item.PokemonItem
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun Route.pokemonList() {
    val allPokemonList = getPokemonListJson()
    get("/pokemonList") {
        respondPagedData(allPokemonList)
    }
    get("/pokemonList/{query}") {
        respondPagedDataByQuery(allPokemonList, call.parameters["query"])
    }
}

private fun getPokemonListJson(): List<PokemonItem> {
    val pokemonJsonString = object {}.javaClass.getResource("/pokemonListData/pokemonDetails.json")?.readText()
    return Json.decodeFromString(pokemonJsonString ?: "")
}

suspend fun PipelineContext<Unit, ApplicationCall>.respondPagedData(data: List<PokemonItem>) {
    val offset = call.request.queryParameters["offset"]?.toLongOrNull() ?: 0
    val limit = call.request.queryParameters["limit"]?.toLongOrNull() ?: 20
    if (limit < 0L || offset < 0L)
        call.respond(
            HttpStatusCode.BadRequest,
            ErrorResponse(HttpStatusCode.BadRequest.value, "Limit and offset cannot be lower than 0")
        )
    else
        call.respond(HttpStatusCode.OK, data.stream().skip(offset).limit(limit).toList())
}

suspend fun PipelineContext<Unit, ApplicationCall>.respondPagedDataByQuery(
    data: List<PokemonItem>,
    searchQuery: String?
) {
    if (searchQuery.isNullOrBlank())
        respondPagedData(data)
    else
        respondPagedData(data.filterAndSortListBy(searchQuery))
}