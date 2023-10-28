package io.github.qualterz.cmarkup.abstractions

import io.github.qualterz.cmarkup.data.ContainerMarkup

interface IMarkupStorage {
    fun removeMarkup(name: String)
    fun saveMarkup(name: String, markup: ContainerMarkup)
    fun loadMarkup(name: String): ContainerMarkup
    fun listMarkups(): Array<String>
    fun isMarkupExists(name: String): Boolean
}