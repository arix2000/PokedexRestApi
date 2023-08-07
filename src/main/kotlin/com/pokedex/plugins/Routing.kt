package com.pokedex.plugins

import com.pokedex.routes.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    
    routing {
        pokemonList()
        moveList()
        itemList()
        abilityList()
        locationList()
        static {
            resources("pokemonListData")
        }
    }
}
