package com.pokedex.routes

import com.pokedex.extensions.filterAndSortListBy
import com.pokedex.models.ability_item.AbilityItem
import com.pokedex.models.common.ErrorResponse
import com.pokedex.models.common.SearchableByName
import com.pokedex.models.item.Item
import com.pokedex.models.location_item.LocationItem
import com.pokedex.models.move_item.MoveItem
import com.pokedex.models.pokemon_item.PokemonItem
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun Route.pokemonList() {
    val allPokemonList: List<PokemonItem> = getItemListJson("pokemonList.json")
    get("/pokemonList") {
        respondPagedData(allPokemonList)
    }
    get("/pokemonList/{query}") {
        respondPagedDataByQuery(allPokemonList, call.parameters["query"])
    }
}

fun Route.moveList() {
    val allMoveList: List<MoveItem> = getItemListJson("moveList.json")
    get("/moveList") {
        respondPagedData(allMoveList)
    }
    get("/moveList/{query}") {
        respondPagedDataByQuery(allMoveList, call.parameters["query"])
    }
}

fun Route.abilityList() {
    val allAbilityList: List<AbilityItem> = getItemListJson("abilityList.json")
    get("/abilityList") {
        respondPagedData(allAbilityList)
    }
    get("/abilityList/{query}") {
        respondPagedDataByQuery(allAbilityList, call.parameters["query"])
    }
}

fun Route.itemList() {
    val allItemList: List<Item> = getItemListJson("itemList.json")
    get("/itemList") {
        respondPagedData(allItemList)
    }
    get("/itemList/{query}") {
        respondPagedDataByQuery(allItemList, call.parameters["query"])
    }
}

fun Route.locationList() {
    val allLocationList: List<LocationItem> = getItemListJson("locationList.json")
    get("/locationList") {
        respondPagedData(allLocationList)
    }
    get("/locationList/{query}") {
        respondPagedDataByQuery(allLocationList, call.parameters["query"])
    }
}

private inline fun <reified T> getItemListJson(fileName: String): List<T> {
    val pokemonJsonString = object {}.javaClass.getResource("/$fileName")?.readText()
    return Json.decodeFromString<List<T>>(pokemonJsonString ?: "")
}

private suspend inline fun <reified T> PipelineContext<Unit, ApplicationCall>.respondPagedData(data: List<T>) {
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

private suspend inline fun <reified T: SearchableByName> PipelineContext<Unit, ApplicationCall>.respondPagedDataByQuery(
    data: List<T>,
    searchQuery: String?
) {
    if (searchQuery.isNullOrBlank())
        respondPagedData(data)
    else
        respondPagedData(data.filterAndSortListBy(searchQuery))
}