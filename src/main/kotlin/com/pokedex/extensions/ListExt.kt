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

fun <T: SearchableByName> List<T>.filterByNames(names: List<String>): List<T> {
    return this.filter { names.map { name -> name.lowercase() }.contains(it.name) }
}