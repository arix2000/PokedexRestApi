package com.pokedex.routes

import io.ktor.server.routing.*

private const val BASE_URL = "http://192.168.1.21:8080"

fun Route.pokemonList() {
    get("/pokemonList") {

    }
    get("/pokemonList/{query}") {

    }
}