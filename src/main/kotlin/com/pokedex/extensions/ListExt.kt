package com.pokedex.extensions

import com.pokedex.models.common.SearchableByName

fun <T: SearchableByName> List<T>.filterAndSortListBy(query: String): List<T> {
    val filteredList = this.filter { it.name.contains(query) }
    val filteredAndSorted = filteredList
        .filter { it.name.startsWith(query) }
        .toMutableList()
    filteredList.forEach {
        if (!filteredAndSorted.contains(it)) filteredAndSorted.add(it)
    }
    return filteredAndSorted
}