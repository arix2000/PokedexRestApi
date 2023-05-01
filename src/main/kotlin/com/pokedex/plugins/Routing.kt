package com.pokedex.plugins

import com.pokedex.routes.pokemonList
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    
    routing {
        pokemonList()
        static {
            resources("pokemonListData")
        }
    }
}
